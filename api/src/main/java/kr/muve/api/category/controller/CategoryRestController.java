package kr.muve.api.category.controller;

import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.category.AllCategory;
import kr.muve.common.service.category.CategoryAllRes;
import kr.muve.common.service.category.CategoryProductsRes;
import kr.muve.common.service.category.ProductsCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryRestController {

    private final AllCategory allCategory;
    private final ProductsCategory productsCategory;

    @GetMapping()
    public CommonResponse<List<CategoryAllRes>> getCategoryList() {
        return CommonResponse.success(allCategory.getCategoryList());
    }

    @GetMapping("/products")
    public CommonResponse<CategoryProductsRes> getCategoryProducts(@RequestParam("categoryId") Long categoryId,
                                                   @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(name = "size",required = false, defaultValue = "10") Integer size) {

        log.info("[GET] /category/products, categoryId: {}, page: {}, size: {}", categoryId, page, size);

        return CommonResponse.success(productsCategory.getCategoryProducts(categoryId, page, size));
    }
}
