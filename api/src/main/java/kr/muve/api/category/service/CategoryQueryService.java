package kr.muve.api.category.service;

import com.google.common.base.Preconditions;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.repository.category.SpringDataCategoryRepository;
import kr.muve.common.repository.product.SpringDataProductRepository;
import kr.muve.common.service.category.AllCategory;
import kr.muve.common.service.category.CategoryRes;
import kr.muve.common.service.category.CategoryProductsRes;
import kr.muve.common.service.category.ProductsCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryQueryService implements AllCategory, ProductsCategory {

    private final SpringDataCategoryRepository categoryRepository;
    private final SpringDataProductRepository productRepository;

    @Override
    public List<CategoryRes> getCategoryList() {
        return CategoryRes.from(categoryRepository.findAll());
    }

    @Override
    public CategoryProductsRes getCategoryProducts(Long id, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Preconditions.checkArgument(categoryRepository.existsById(id), new BaseException(ErrorCode.CATEGORY_NOT_FOUND));

        Page<ProductJpaEntity> productPage = productRepository.findByCategoryIdWithPage(id, pageable);
        return CategoryProductsRes.from(productPage);

    }
}


