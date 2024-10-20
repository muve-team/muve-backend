package kr.muve.admin.service;

import kr.muve.common.domain.category.Category;
import kr.muve.common.domain.product.Product;
import kr.muve.common.repository.category.CategoryRepository;
import kr.muve.common.repository.product.ProductRepository;
import kr.muve.common.service.product.CreateProduct;
import kr.muve.common.service.product.FindProducts;
import kr.muve.common.service.product.ProductDto;
import kr.muve.common.service.product.UpdateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService implements CreateProduct, UpdateProduct, FindProducts {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;


    // 상품 등록
    @Override
    @Transactional
    public Long create(ProductDto dto) {
        Long categoryId = dto.getCategoryId();
        Category category = categoryRepository.findOne(categoryId);

        Product product = Product.createProduct(dto.getName(), dto.getPrice(),
                dto.getStockQuantity(), category);
        productRepository.save(product);

        return product.getId();
    }

    // 상품 전체 조회
    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    // 상품 조회
    @Override
    public Product findOne(Long productId) {
        return productRepository.findOne(productId);
    }

    // 상품 수정
    @Override
    @Transactional
    public void update(ProductDto dto) {
        Product product = findOne(dto.getId());
        Category category = categoryRepository.findOne(dto.getCategoryId());
        product.update(dto, category);
    }
}