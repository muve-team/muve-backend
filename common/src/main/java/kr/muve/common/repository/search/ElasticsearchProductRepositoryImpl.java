package kr.muve.common.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import kr.muve.common.domain.product.ProductElasticsearchEntity;
import kr.muve.common.repository.search.ElasticsearchProductCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class ElasticsearchProductRepositoryImpl implements ElasticsearchProductCustomRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public Page<ProductElasticsearchEntity> searchByKeyword(String keyword, Pageable pageable) {
        if (!StringUtils.hasText(keyword)) {
            return Page.empty(pageable);
        }

        // 정확한 매칭 쿼리
        Query koreanNameMatchQuery = QueryBuilders.match()
                .field("koreanName")
                .query(keyword)
                .boost(2.0f)
                .build()._toQuery();

        Query englishNameMatchQuery = QueryBuilders.match()
                .field("englishName")
                .query(keyword)
                .boost(2.0f)
                .build()._toQuery();

        Query brandKoreanNameMatchQuery = QueryBuilders.match()
                .field("brandKoreanName")
                .query(keyword)
                .boost(1.5f)
                .build()._toQuery();

        Query brandEnglishNameMatchQuery = QueryBuilders.match()
                .field("brandEnglishName")
                .query(keyword)
                .boost(1.5f)
                .build()._toQuery();

        Query categoryNameMatchQuery = QueryBuilders.match()
                .field("categoryName")
                .query(keyword)
                .boost(1.0f)
                .build()._toQuery();

        // Fuzzy 매칭 쿼리 (오타 허용)
        Query koreanNameFuzzyQuery = QueryBuilders.fuzzy()
                .field("koreanName")
                .value(keyword)
                .fuzziness("AUTO")
                .boost(1.5f)
                .build()._toQuery();

        Query englishNameFuzzyQuery = QueryBuilders.fuzzy()
                .field("englishName")
                .value(keyword)
                .fuzziness("AUTO")
                .boost(1.5f)
                .build()._toQuery();

        // Prefix 매칭 쿼리 (시작 부분 일치)
        Query koreanNamePrefixQuery = QueryBuilders.prefix()
                .field("koreanName")
                .value(keyword)
                .boost(1.8f)
                .build()._toQuery();

        Query englishNamePrefixQuery = QueryBuilders.prefix()
                .field("englishName")
                .value(keyword)
                .boost(1.8f)
                .build()._toQuery();

        // Phrase 매칭 쿼리 (구문 일치)
        Query koreanNamePhraseQuery = QueryBuilders.matchPhrase()
                .field("koreanName")
                .query(keyword)
                .boost(2.5f)
                .build()._toQuery();

        Query englishNamePhraseQuery = QueryBuilders.matchPhrase()
                .field("englishName")
                .query(keyword)
                .boost(2.5f)
                .build()._toQuery();

        // Bool 쿼리 조합
        BoolQuery boolQuery = QueryBuilders.bool()
                // 정확한 매칭
                .should(koreanNameMatchQuery)
                .should(englishNameMatchQuery)
                .should(brandKoreanNameMatchQuery)
                .should(brandEnglishNameMatchQuery)
                .should(categoryNameMatchQuery)
                // Fuzzy 매칭
                .should(koreanNameFuzzyQuery)
                .should(englishNameFuzzyQuery)
                // Prefix 매칭
                .should(koreanNamePrefixQuery)
                .should(englishNamePrefixQuery)
                // Phrase 매칭
                .should(koreanNamePhraseQuery)
                .should(englishNamePhraseQuery)
                .minimumShouldMatch("1")
                .build();

        // Native 쿼리 생성
        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(boolQuery._toQuery())
                .withPageable(pageable)
                .withSort(Sort.by(Sort.Direction.DESC, "_score")) // 검색 정확도로 정렬
                .build();

        // 검색 실행
        SearchHits<ProductElasticsearchEntity> searchHits = elasticsearchOperations.search(
                searchQuery,
                ProductElasticsearchEntity.class
        );

        List<ProductElasticsearchEntity> products = searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();

        // 페이징 처리된 결과 반환
        return new PageImpl<>(products, pageable, searchHits.getTotalHits());
    }
}