package kr.muve.common.service.category;

public interface ProductsCategory {

    CategoryProductsRes getCategoryProducts(Long id, int page, int size);
}
