package kr.muve.api.product.controller;

import kr.muve.common.service.product.DetailProduct;
import kr.muve.common.service.product.ProductDetailRes;
import kr.muve.common.service.product.RandomProducts;
import kr.muve.common.service.product.ProductsRandomRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product")
public class ProductRestController {

    private final RandomProducts randomProducts;
    private final DetailProduct detailProduct;

    @GetMapping("/random")
    public List<ProductsRandomRes> getRandomProducts() {
        return randomProducts.random();
    }

    @GetMapping("/detail")
    public ProductDetailRes getProductDetail(@RequestParam("productId") Long productId) {
        return detailProduct.getProductDetail(productId);
    }
}
