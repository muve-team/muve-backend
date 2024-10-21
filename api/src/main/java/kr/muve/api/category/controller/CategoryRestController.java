package kr.muve.api.category.controller;

import kr.muve.common.service.category.AllCategory;
import kr.muve.common.service.category.CategoryAllRes;
import kr.muve.common.service.category.CategoryProductsRes;
import kr.muve.common.service.category.ProuductsCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryRestController {

    private final AllCategory allCategory;
    private final ProuductsCategory prouductsCategory;

    @GetMapping
    public List<CategoryAllRes> getCategoryList() {
        return allCategory.getCategoryList();
    }

    @GetMapping("/{categoryId}")
    public CategoryProductsRes getCategoryProducts(@PathVariable("categoryId") Long categoryId,
                                                         @RequestParam int page, @RequestParam int size) {
        return prouductsCategory.getCategoryProducts(categoryId, page, size);
    }
}
