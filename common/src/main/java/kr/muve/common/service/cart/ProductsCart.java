package kr.muve.common.service.cart;

import java.util.List;

public interface ProductsCart {
    List<CartProducts> getCartProducts(Long userId, Integer page, Integer size);
}
