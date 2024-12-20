package kr.muve.api.search.controller;

import kr.muve.api.search.history.service.SearchHistoryService;
import kr.muve.common.exception.CommonResponse;
import kr.muve.common.service.search.ProductsSearch;
import kr.muve.common.service.search.SearchProductsRes;
import kr.muve.common.service.search.history.HottestSearchesRes;
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
    private final SearchHistoryService searchHistoryService;

    @GetMapping
    public CommonResponse<SearchProductsRes> getSearchProducts(@RequestParam("keyword") String keyword,
                                                                     @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                     @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        log.info("[GET] /search, keyword: {}, page: {}, size: {}", keyword, page, size);

        return CommonResponse.success(productsSearch.getSearchProducts(keyword, page, size));
    }

    @GetMapping("/hottest")
    public CommonResponse<HottestSearchesRes> getHottestSearchTerms(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                                                    @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

        log.info("[GET] /search/hottest, page: {}, size: {}", page, size);

        return CommonResponse.success(searchHistoryService.getHottest(size));
    }

    @GetMapping("/autocomplete")
    public CommonResponse<List<String>> getAutoComplete(
            @RequestParam("keyword") String keyword,
            @RequestParam(name = "size", required = false, defaultValue = "5") Integer size
    ) {
        log.info("[GET] /search/autocomplete, keyword: {}, size: {}", keyword, size);

        return CommonResponse.success(searchHistoryService.getAutoComplete(keyword, size));
    }
}