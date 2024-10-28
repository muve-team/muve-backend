package kr.muve.api.product.controller;

import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.product.DetailProduct;
import kr.muve.common.service.product.ProductDetailRes;
import kr.muve.common.service.product.RandomProducts;
import kr.muve.common.service.product.ProductsRandomRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product")
public class ProductRestController {

    private final RandomProducts randomProducts;
    private final DetailProduct detailProduct;

    @GetMapping("/random")
    public CommonResponse<List<ProductsRandomRes>> getRandomProducts() {

        log.info("[GET] /product/random");

        return CommonResponse.success(randomProducts.random());
    }

    @GetMapping("/detail")
    public CommonResponse<ProductDetailRes> getProductDetail(@RequestParam("productId") Long productId) {

        log.info("[GET] /product/detail, productId: {}", productId);

        return CommonResponse.success(detailProduct.getProductDetail(productId));
    }
}
