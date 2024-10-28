package kr.muve.api.cart.controller;

import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.cart.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartRestController {

    private final ProductsCart productsCart;
    private final AddCartProduct addCartProduct;
    private final UpdateCartProductCount updateCartProductCount;
    private final DeleteCartProducts deleteCartProducts;

    @GetMapping
    public CommonResponse<List<CartProducts>> getCartProducts(@RequestParam("userId") Long id,
                                                             @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        log.info("[GET] /cart, userId: {}, page: {}, size: {}", id, page, size);

        return CommonResponse.success(productsCart.getCartProducts(id,page, size));
    }

    @PostMapping
    public CommonResponse<Long> addCartProduct(@RequestBody CartProductAddCommand command) {

        log.info("[POST] /cart, userId: {}, productId: {}, count: {}",
                command.getUserId(), command.getProductId(), command.getCount());

        return CommonResponse.success(addCartProduct.add(command));
    }

    @PutMapping
    public CommonResponse<Long> updateCartProductCount(@RequestBody CartProductUpdateCountCommand command) {

        log.info("[PUT] /cart, cartProductId: {}, newCount: {}",
                command.getCartProductId(), command.getCount());

        return CommonResponse.success(updateCartProductCount.update(command));
    }

    @DeleteMapping
    public CommonResponse<Long> deleteCartProducts(@RequestBody CartProductsDeleteCommand command) {

        log.info("[DELETE] /cart, ids: {}", command.getIds());

        return CommonResponse.success(deleteCartProducts.delete(command));
    }


}
