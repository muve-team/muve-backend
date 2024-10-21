package kr.muve.common.service.category;

public interface ProuductsCategory {

    CategoryProductsRes getCategoryProducts(Long id, int page, int size);
}
