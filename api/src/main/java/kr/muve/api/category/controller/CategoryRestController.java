package kr.muve.api.category.controller;

import kr.muve.common.service.category.AllCategory;
import kr.muve.common.service.category.CategoryAllRes;
import kr.muve.common.service.category.CategoryProductsRes;
import kr.muve.common.service.category.ProductsCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {

    private final AllCategory allCategory;
    private final ProductsCategory productsCategory;

    @GetMapping
    public List<CategoryAllRes> getCategoryList() {
        return allCategory.getCategoryList();
    }

    @GetMapping("/category")
    public CategoryProductsRes getCategoryProducts(@RequestParam("categoryId") Long categoryId,
                                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(required = false, defaultValue = "10") Integer size) {

        return productsCategory.getCategoryProducts(categoryId, page, size);
    }
}
