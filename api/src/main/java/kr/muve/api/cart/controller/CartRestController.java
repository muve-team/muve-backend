package kr.muve.api.cart.controller;

import kr.muve.common.service.cart.CartProducts;
import kr.muve.common.service.cart.ProductsCart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartRestController {

    private final ProductsCart productsCart;

    @GetMapping()
    public List<CartProducts> getCartProducts(@RequestParam("userId") Long id,
                                              @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                              @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return productsCart.getCartProducts(id,page, size);
    }


//    @PostMapping()
//    public
}
