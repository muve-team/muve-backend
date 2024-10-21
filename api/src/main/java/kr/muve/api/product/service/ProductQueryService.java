package kr.muve.api.product.service;

import kr.muve.common.repository.product.SpringDataProductRepository;
import kr.muve.common.service.product.ProductDetail;
import kr.muve.common.service.product.ProductDetailRes;
import kr.muve.common.service.product.RandomProducts;
import kr.muve.common.service.product.RandomProductsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductQueryService implements RandomProducts, ProductDetail {

    private final SpringDataProductRepository productRepository;

    @Override
    public List<RandomProductsRes> random() {
        Pageable pageable = PageRequest.of(0, 10);
        return RandomProductsRes.from(productRepository.findRandomProducts(pageable));
    }

    @Override
    public ProductDetailRes getProductDetail(Long id) {
        return ProductDetailRes.from(productRepository.findById(id));
    }

}
