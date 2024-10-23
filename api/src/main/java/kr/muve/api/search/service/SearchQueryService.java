package kr.muve.api.search.service;

import kr.muve.common.repository.product.SpringDataProductRepository;
import kr.muve.common.service.search.ProductsSearch;
import kr.muve.common.service.search.SearchProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchQueryService implements ProductsSearch {

    private final SpringDataProductRepository productRepository;

    @Override
    public List<SearchProducts> getSearchProducts(String keyword, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return SearchProducts.from(productRepository.findAllWithKeyword(keyword, pageable));
    }
}
