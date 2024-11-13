package kr.muve.common.repository.product;

import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import kr.muve.common.domain.product.ProductElasticsearchEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ElasticsearchProductRepositoryImpl implements ElasticsearchProductCustomRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public Page<ProductElasticsearchEntity> searchByKeyword(String keyword, Pageable pageable) {
        // 검색어가 비어있는 경우 처리
        if (!StringUtils.hasText(keyword)) {
            return Page.empty(pageable);
        }

        // 검색 쿼리 생성
        Query nameQuery = QueryBuilders.match()
                .field("name")
                .query(keyword)
                .boost(2.0f)
                .build()._toQuery();

        Query categoryQuery = QueryBuilders.match()
                .field("categoryName")
                .query(keyword)
                .boost(1.0f)
                .build()._toQuery();

        // Bool 쿼리 생성
        BoolQuery boolQuery = QueryBuilders.bool()
                .should(nameQuery)
                .should(categoryQuery)
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
                .collect(Collectors.toList());

        // 페이징 처리된 결과 반환
        return new PageImpl<>(products, pageable, searchHits.getTotalHits());
    }

}