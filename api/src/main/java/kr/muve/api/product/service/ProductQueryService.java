package kr.muve.api.product.service;

import com.google.common.base.Preconditions;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.exception.ProductNotFoundException;
import kr.muve.common.repository.product.SpringDataProductRepository;
import kr.muve.common.service.category.CategoryProductsRes;
import kr.muve.common.service.product.*;
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
public class ProductQueryService implements RandomProducts, TimeDealProducts, DetailProduct, NewestProducts {

    private final SpringDataProductRepository productRepository;

    @Override
    public List<ProductsRandomRes> random() {
        Pageable pageable = PageRequest.of(0, 10);
        return ProductsRandomRes.from(productRepository.findRandomProducts(pageable));
    }

    @Override
    public List<TimeDealProductsRes> getTimeDealProducts() {
        return TimeDealProductsRes.from(productRepository.findAllTimeDealsBetweenStartAndEnd());
    }

    @Override
    public ProductDetailRes getProductDetail(Long id) {
        ProductJpaEntity product = productRepository.findById(id)
                .orElseThrow(() -> new BaseException(ErrorCode.PRODUCT_NOT_FOUND));

        return ProductDetailRes.from(product);
    }

    @Override
    public NewestProductsRes getNewestProducts(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<ProductJpaEntity> productPage = productRepository.findAllByCreatedDateDesc(pageable);
        return NewestProductsRes.from(productPage);
    }
}

