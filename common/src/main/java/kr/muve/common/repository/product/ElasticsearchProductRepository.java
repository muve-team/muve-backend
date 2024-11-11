package kr.muve.common.repository.product;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticsearchProductRepository extends ElasticsearchRepository<ProductElasticsearchEntity, Long> {
    // 이름으로 검색
    List<ProductElasticsearchEntity> findByNameContaining(String name);

    // 카테고리로 검색
    List<ProductElasticsearchEntity> findByCategoryName(String categoryName);

    // 가격 범위로 검색
    List<ProductElasticsearchEntity> findByPriceBetween(Long minPrice, Long maxPrice);

    // 재고가 있는 상품 검색
    List<ProductElasticsearchEntity> findByStockQuantityGreaterThan(Integer minStock);
}