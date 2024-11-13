package kr.muve.common.repository.search;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import kr.muve.common.domain.product.ProductElasticsearchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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
        // 검색어가 비어있는 경우 처리
        if (!StringUtils.hasText(keyword)) {
            return Page.empty(pageable);
        }

        // 각 필드에 대한 검색 쿼리 생성
        Query koreanNameQuery = QueryBuilders.match()
                .field("koreanName")
                .query(keyword)
                .boost(2.0f)
                .build()._toQuery();

        Query englishNameQuery = QueryBuilders.match()
                .field("englishName")
                .query(keyword)
                .boost(2.0f)
                .build()._toQuery();

        Query brandKoreanNameQuery = QueryBuilders.match()
                .field("brandKoreanName")
                .query(keyword)
                .boost(1.5f)
                .build()._toQuery();

        Query brandEnglishNameQuery = QueryBuilders.match()
                .field("brandEnglishName")
                .query(keyword)
                .boost(1.5f)
                .build()._toQuery();

        Query categoryNameQuery = QueryBuilders.match()
                .field("categoryName")
                .query(keyword)
                .boost(1.0f)
                .build()._toQuery();

        // Bool 쿼리 생성
        BoolQuery boolQuery = QueryBuilders.bool()
                .should(koreanNameQuery)
                .should(englishNameQuery)
                .should(brandKoreanNameQuery)
                .should(brandEnglishNameQuery)
                .should(categoryNameQuery)
                .minimumShouldMatch("1")
                .build();

        // Native 쿼리 생성
        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(boolQuery._toQuery())
                .withPageable(pageable)
                .build();

        // 검색 실행
        SearchHits<ProductElasticsearchEntity> searchHits = elasticsearchOperations.search(
                searchQuery,
                ProductElasticsearchEntity.class
        );

        // 결과 변환
        List<ProductElasticsearchEntity> products = searchHits.stream()
                .map(SearchHit::getContent)
                .toList();

        // 페이징 처리된 결과 반환
        return new PageImpl<>(products, pageable, searchHits.getTotalHits());
    }

    @Override
    public List<ProductElasticsearchEntity> findSimilarKeywords(String keyword, int size) {
        if (!StringUtils.hasText(keyword)) {
            return List.of();
        }

        // 각 필드별 퍼지 매칭 쿼리 생성
        Query koreanNameQuery = QueryBuilders.fuzzy()
                .field("koreanName")
                .value(keyword)
                .fuzziness("AUTO")
                .boost(2.0f)
                .build()._toQuery();

        Query englishNameQuery = QueryBuilders.fuzzy()
                .field("englishName")
                .value(keyword)
                .fuzziness("AUTO")
                .boost(2.0f)
                .build()._toQuery();

        Query brandKoreanNameQuery = QueryBuilders.fuzzy()
                .field("brandKoreanName")
                .value(keyword)
                .fuzziness("AUTO")
                .boost(1.5f)
                .build()._toQuery();

        Query brandEnglishNameQuery = QueryBuilders.fuzzy()
                .field("brandEnglishName")
                .value(keyword)
                .fuzziness("AUTO")
                .boost(1.5f)
                .build()._toQuery();

        Query categoryNameQuery = QueryBuilders.fuzzy()
                .field("categoryName")
                .value(keyword)
                .fuzziness("AUTO")
                .boost(1.0f)
                .build()._toQuery();

        // 각 필드별 prefix 매칭 쿼리 생성
        Query koreanNamePrefixQuery = QueryBuilders.prefix()
                .field("koreanName")
                .value(keyword)
                .boost(2.0f)
                .build()._toQuery();

        Query englishNamePrefixQuery = QueryBuilders.prefix()
                .field("englishName")
                .value(keyword)
                .boost(2.0f)
                .build()._toQuery();

        // Bool 쿼리로 조합
        BoolQuery boolQuery = QueryBuilders.bool()
                .should(koreanNameQuery)
                .should(englishNameQuery)
                .should(brandKoreanNameQuery)
                .should(brandEnglishNameQuery)
                .should(categoryNameQuery)
                .should(koreanNamePrefixQuery)
                .should(englishNamePrefixQuery)
                .minimumShouldMatch("1")
                .build();

        // Native 쿼리 생성
        NativeQuery searchQuery = NativeQuery.builder()
                .withQuery(boolQuery._toQuery())
                .withSort(Sort.by(Sort.Direction.DESC, "_score"))
                .withPageable(PageRequest.of(0, size))
                .build();

        // 검색 실행
        SearchHits<ProductElasticsearchEntity> searchHits = elasticsearchOperations.search(
                searchQuery,
                ProductElasticsearchEntity.class
        );

        // 결과 변환 및 반환
        return searchHits.stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
