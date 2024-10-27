package kr.muve.api.cart.controller;

import kr.muve.common.service.cart.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartRestController {

    private final ProductsCart productsCart;
    private final AddCartProduct addCartProduct;
    private final UpdateCartProductCount updateCartProductCount;
    private final DeleteCartProducts deleteCartProducts;

    @GetMapping
    public List<CartProducts> getCartProducts(@RequestParam("userId") Long id,
                                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return productsCart.getCartProducts(id,page, size);
    }

    @PostMapping
    public Long addCartProduct(@RequestBody CartProductAddCommand command) {
        return addCartProduct.add(command);
    }

    @PutMapping
    public Long updateCartProductCount(@RequestBody CartProductUpdateCountCommand command) {
        return updateCartProductCount.update(command);
    }

    @DeleteMapping
    public String deleteCartProducts(@RequestBody CartProductsDeleteCommand command) {
        return deleteCartProducts.delete(command);
    }


}
