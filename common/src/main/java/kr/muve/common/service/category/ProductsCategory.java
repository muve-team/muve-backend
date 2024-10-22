package kr.muve.common.service.category;

public interface ProductsCategory {

    CategoryProductsRes getCategoryProducts(Long id, Integer page, Integer size);
}
