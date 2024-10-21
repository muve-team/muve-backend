package kr.muve.api.category.controller;

import kr.muve.common.service.category.AllCategory;
import kr.muve.common.service.category.CategoryAllRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/categories")
public class CategoryRestController {

    private final AllCategory allCategory;

    @GetMapping
    public List<CategoryAllRes> getCategoryList() {
        return allCategory.getCategoryList();
    }

//    @GetMapping("/{categoryId}")
//    public List<CategoryProductRes> getCategoryProducts(@PathVariable("categoryId") Long categoryId) {
//    }
}
