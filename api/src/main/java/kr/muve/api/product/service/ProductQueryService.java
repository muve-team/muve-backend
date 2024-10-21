package kr.muve.api.product.service;

import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.exception.ProductNotFoundException;
import kr.muve.common.repository.product.SpringDataProductRepository;
import kr.muve.common.service.product.DetailProduct;
import kr.muve.common.service.product.ProductDetailRes;
import kr.muve.common.service.product.RandomProducts;
import kr.muve.common.service.product.ProductsRandomRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryService implements RandomProducts, DetailProduct {

    private final SpringDataProductRepository productRepository;

    @Override
    public List<ProductsRandomRes> random() {
        Pageable pageable = PageRequest.of(0, 10);
        return ProductsRandomRes.from(productRepository.findRandomProducts(pageable));
    }

    @Override
    public ProductDetailRes getProductDetail(Long id) {
        ProductJpaEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("상품을 찾을 수 없습니다."));

        return ProductDetailRes.from(product);
    }
}
