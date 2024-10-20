package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;

import java.util.List;

public interface FindProducts {

    ProductJpaEntity findById(Long id);

    List<ProductJpaEntity> findProducts();
}
