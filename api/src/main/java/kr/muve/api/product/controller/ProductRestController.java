package kr.muve.api.product.controller;

import kr.muve.common.service.product.RandomProducts;
import kr.muve.common.service.product.RandomProductsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product")
public class ProductRestController {

    private final RandomProducts randomProducts;

    @GetMapping("/random")
    public List<RandomProductsRes> getRandomProducts() {
        return randomProducts.random();
    }

    @GetMapping("/{productId}")
    public void getProductDetail(@PathVariable("productId") Long productId) {

    }
}
