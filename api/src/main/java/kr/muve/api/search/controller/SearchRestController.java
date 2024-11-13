package kr.muve.api.search.controller;

import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.search.ProductsSearch;
import kr.muve.common.service.search.SearchProductsRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchRestController {

    private final ProductsSearch productsSearch;

    @GetMapping
    public CommonResponse<SearchProductsRes> getSearchProducts(@RequestParam("keyword") String keyword,
                                                                     @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        log.info("[GET] /search, keyword: {}, page: {}, size: {}", keyword, page, size);

        return CommonResponse.success(productsSearch.getSearchProducts(keyword, page, size));
    }

}