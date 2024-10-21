package kr.muve.api.category.service;

import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.exception.CategoryNotFoundException;
import kr.muve.common.repository.category.SpringDataCategoryRepository;
import kr.muve.common.repository.product.SpringDataProductRepository;
import kr.muve.common.service.category.AllCategory;
import kr.muve.common.service.category.CategoryAllRes;
import kr.muve.common.service.category.CategoryProductsRes;
import kr.muve.common.service.category.ProuductsCategory;
import kr.muve.common.service.product.ProductListRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryQueryService implements AllCategory, ProuductsCategory {

    private final SpringDataCategoryRepository categoryRepository;
    private final SpringDataProductRepository productRepository;

    @Override
    public List<CategoryAllRes> getCategoryList() {
        return CategoryAllRes.from(categoryRepository.findAll());
    }

    @Override
    public CategoryProductsRes getCategoryProducts(Long id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        CategoryJpaEntity category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("카테고리를 찾을 수 없습니다."));

        List<ProductJpaEntity> products = productRepository.findAllByCategoryId(id, pageable);
        List<ProductListRes> productList = ProductListRes.from(products);

        return CategoryProductsRes.from(category, productList);

    }
}
