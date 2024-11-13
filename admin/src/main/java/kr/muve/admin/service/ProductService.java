package kr.muve.admin.service;

import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.product.ProductElasticsearchEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.exception.ProductNotFoundException;
import kr.muve.common.repository.search.ElasticsearchProductRepository;
import kr.muve.common.repository.product.SpringDataProductRepository;
import kr.muve.common.service.product.CreateProduct;
import kr.muve.common.service.product.FindProducts;
import kr.muve.common.service.product.ProductDto;
import kr.muve.common.service.product.UpdateProduct;
import kr.muve.common.service.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService implements CreateProduct, UpdateProduct, FindProducts {

    private final SpringDataProductRepository productRepository;
    private final CategoryService categoryService;
    private final S3Service s3Service;
    private final ElasticsearchProductRepository elasticsearchProductRepository;

    // 상품 등록
    @Override
    @Transactional
    public Long create(ProductDto dto) {
        Long categoryId = dto.getCategoryId();
        CategoryJpaEntity categoryJpaEntity = categoryService.findById(categoryId);

        MultipartFile image = dto.getImage();

        if (ObjectUtils.isEmpty(image)) {
            throw new IllegalArgumentException("이미지를 찾을 수 없습니다.");
        }

        String imageUrl = s3Service.uploadFile(image);

        ProductJpaEntity productJpaEntity = ProductJpaEntity.createProduct(dto.getKoreanName(), dto.getEnglishName(),
                dto.getBrandKoreanName(), dto.getBrandEnglishName(), dto.getPrice(),
                dto.getStockQuantity(), imageUrl, categoryJpaEntity);
        productRepository.saveAndFlush(productJpaEntity);

        ProductElasticsearchEntity elasticsearchEntity = ProductElasticsearchEntity.from(productJpaEntity);
        elasticsearchProductRepository.save(elasticsearchEntity);

        return productJpaEntity.getId();
    }

    // 상품 전체 조회
    @Override
    public List<ProductJpaEntity> findProducts() {
        return productRepository.findAll();
    }

    // 상품 조회
    @Override
    public ProductJpaEntity findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("상품을 찾을 수 없습니다."));
    }

    // 상품 수정
    @Override
    @Transactional
    public void update(ProductDto dto) {
        MultipartFile image = dto.getImage();

        String imageUrl = uploadImageWhenImageExist(image);

        ProductJpaEntity productJpaEntity = findById(dto.getId());
        CategoryJpaEntity categoryJpaEntity = categoryService.findById(dto.getCategoryId());
        productJpaEntity.update(dto, categoryJpaEntity, imageUrl);
    }

    private String uploadImageWhenImageExist(MultipartFile image) {
        if (!ObjectUtils.isEmpty(image) && !image.isEmpty()) {
            return s3Service.uploadFile(image);
        }
        return null;
    }
}