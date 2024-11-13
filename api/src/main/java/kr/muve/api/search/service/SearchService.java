package kr.muve.api.search.service;

import kr.muve.api.search.history.service.SearchHistoryService;
import kr.muve.common.repository.search.ElasticsearchProductRepository;
import kr.muve.common.service.search.ProductsSearch;
import kr.muve.common.service.search.SearchProductsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService implements ProductsSearch {

    private final ElasticsearchProductRepository productRepository;
    private final SearchHistoryService searchHistoryService;

    @Override
    public SearchProductsRes getSearchProducts(String keyword, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        searchHistoryService.add(keyword);

        return SearchProductsRes.from(productRepository.searchByKeyword(keyword, pageable));
    }
}
