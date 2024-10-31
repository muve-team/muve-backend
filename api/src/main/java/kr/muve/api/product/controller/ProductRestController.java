package kr.muve.api.product.controller;

import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.product.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductRestController {

    private final RandomProducts randomProducts;
    private final DetailProduct detailProduct;
    private final NewestProducts newestProducts;

    @GetMapping("/products/random")
    public CommonResponse<List<ProductsRandomRes>> getRandomProducts() {

        log.info("[GET] /products/random");

        return CommonResponse.success(randomProducts.random());
    }

    @GetMapping("/product/detail")
    public CommonResponse<ProductDetailRes> getProductDetail(@RequestParam("productId") Long productId) {

        log.info("[GET] /product/detail, productId: {}", productId);

        return CommonResponse.success(detailProduct.getProductDetail(productId));
    }

    @GetMapping("/products/newest")
    public CommonResponse<NewestProductsRes> getNewestProducts(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size",required = false, defaultValue = "10") Integer size
    ) {

        log.info("[GET] /products/newest, page: {}, size: {}", page, size);

        return CommonResponse.success(newestProducts.getNewestProducts(page, size));
    }
}
