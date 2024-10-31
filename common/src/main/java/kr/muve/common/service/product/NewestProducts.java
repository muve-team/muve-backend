package kr.muve.common.service.product;

public interface NewestProducts {
    NewestProductsRes getNewestProducts(Integer page, Integer size);
}