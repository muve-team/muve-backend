package kr.jinsang.sshop.service.product;

import kr.jinsang.sshop.domain.product.Product;
import kr.jinsang.sshop.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;


    // 상품 등록
    @Transactional
    public Long create(ProductDto dto) {
        Product product = Product.createProduct(dto.getName(), dto.getPrice(), dto.getStockQuantity());
        productRepository.save(product);

        return product.getId();
    }

    // 상품 전체 조회
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    // 상품 조회
    public Product findOne(Long productId) {
        return productRepository.findOne(productId);
    }

    // 상품 수정
    @Transactional
    public void update(ProductDto dto) {
        Product product = findOne(dto.getId());
        product.update(dto);
    }




}
