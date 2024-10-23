package kr.muve.api.search.controller;

import kr.muve.common.service.search.ProductsSearch;
import kr.muve.common.service.search.SearchProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchRestController {

    private final ProductsSearch productsSearch;

    @GetMapping()
    public List<SearchProducts> getSearchProducts(@RequestParam("keyword") String keyword,
                                                  @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                  @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        return productsSearch.getSearchProducts(keyword, page, size);
    }

}