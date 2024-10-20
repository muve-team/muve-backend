package kr.muve.common.service.product;

import kr.muve.common.domain.product.Product;

import java.util.List;

public interface FindProducts {

    Product findOne(Long id);

    List<Product> findProducts();
}
