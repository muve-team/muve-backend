package kr.muve.api.search.service;

import kr.muve.common.repository.product.ElasticsearchProductRepository;
import kr.muve.common.repository.product.SpringDataProductRepository;
import kr.muve.common.repository.search.history.MongoSearchHistoryRepository;
import kr.muve.common.service.search.ProductsSearch;
import kr.muve.common.service.search.SearchProductsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService implements ProductsSearch {

    private final ElasticsearchProductRepository productRepository;

    @Override
    public SearchProductsRes getSearchProducts(String keyword, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return SearchProductsRes.from(productRepository.searchByKeyword(keyword, pageable));
    }
}
