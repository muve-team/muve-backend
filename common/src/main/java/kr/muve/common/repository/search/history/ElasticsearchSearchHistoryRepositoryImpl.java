package kr.muve.common.repository.search.history;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import kr.muve.common.domain.search.history.SearchHistoryElasticsearchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class ElasticsearchSearchHistoryRepositoryImpl implements ElasticsearchSearchHistoryCustomRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public void addOrIncrementCount(String keyword) {
        if (!StringUtils.hasText(keyword)) {
            return;
        }

        // keyword.raw를 사용하여 정확한 매칭
        NativeQuery query = NativeQuery.builder()
                .withQuery(q -> q
                        .term(t -> t
                                .field("keyword.raw")
                                .value(keyword)
                        )
                )
                .build();

        SearchHits<SearchHistoryElasticsearchEntity> searchHits =
                elasticsearchOperations.search(query, SearchHistoryElasticsearchEntity.class);

        if (searchHits.hasSearchHits()) {
            // 기존 문서가 있는 경우 count 증가
            SearchHistoryElasticsearchEntity existingEntity = searchHits.getSearchHits().get(0).getContent();
            existingEntity.incrementCount();
            elasticsearchOperations.save(existingEntity);
        } else {
            // 새로운 문서 생성
            SearchHistoryElasticsearchEntity newEntity = new SearchHistoryElasticsearchEntity(keyword);
            elasticsearchOperations.save(newEntity);
        }

        // 즉시 검색 가능하도록 refresh
        elasticsearchOperations.indexOps(SearchHistoryElasticsearchEntity.class).refresh();
    }

    @Override
    public List<SearchHistoryElasticsearchEntity> findHottestSearches(int limit) {
        NativeQuery searchQuery = NativeQuery.builder()
                .withSort(Sort.by(Sort.Direction.DESC, "count"))
                .withPageable(PageRequest.of(0, limit))
                .build();

        SearchHits<SearchHistoryElasticsearchEntity> searchHits =
                elasticsearchOperations.search(searchQuery, SearchHistoryElasticsearchEntity.class);

        return searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
    }

    @Override
    public List<String> findAutoCompleteSuggestions(String prefix, int size) {
        if (!StringUtils.hasText(prefix)) {
            return List.of();
        }

        // 검색어 자동완성을 위한 복합 쿼리
        Query prefixQuery = QueryBuilders.prefix()
                .field("keyword")
                .value(prefix)
                .boost(2.5f) // prefix 쿼리의 가중치 증가
                .build()._toQuery();

        Query matchPhrasePrefixQuery = QueryBuilders.matchPhrasePrefix()
                .field("keyword")
                .query(prefix)
                .boost(1.5f) // matchPhrasePrefix 쿼리의 가중치 설정
                .build()._toQuery();

        Query fuzzyQuery = QueryBuilders.fuzzy()
                .field("keyword")
                .value(prefix)
                .fuzziness("AUTO")
                .prefixLength(1) // 모호성 허용 범위 설정
                .maxExpansions(10) // 검색 확장 수 제한
                .boost(1.2f) // fuzzy 쿼리의 가중치 설정
                .build()._toQuery();

        Query wildcardQuery = QueryBuilders.wildcard()
                .field("keyword")
                .value("*" + prefix + "*") // 검색어 중간에도 해당하는 단어 포함
                .boost(0.8f) // wildcard 쿼리의 낮은 가중치
                .build()._toQuery();

        BoolQuery boolQuery = QueryBuilders.bool()
                .should(prefixQuery)
                .should(matchPhrasePrefixQuery)
                .should(fuzzyQuery)
                .should(wildcardQuery) // wildcard 쿼리 추가
                .minimumShouldMatch("1") // 하나 이상의 쿼리가 일치해야 함
                .build();

        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(boolQuery._toQuery())
                .withSort(Sort.by(
                        Sort.Order.desc("count"),
                        Sort.Order.desc("_score")
                ))
                .withPageable(PageRequest.of(0, size))
                .build();

        SearchHits<SearchHistoryElasticsearchEntity> searchHits =
                elasticsearchOperations.search(searchQuery, SearchHistoryElasticsearchEntity.class);

        return searchHits.getSearchHits().stream()
                .map(hit -> hit.getContent().getKeyword())
                .toList();
    }

}