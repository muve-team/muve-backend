package kr.muve.common.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductForm {
    private Long id;

    @NotBlank(message = "상품명을 작성해주세요")
    private String name;

    @NotNull(message = "상품 가격을 작성해주세요")
    @Positive(message = "가격은 0일 수 없습니다")
    private Long price;

    @NotNull(message = "상품의 갯수를 작성해주세요")
    @PositiveOrZero(message = "갯수는 음수일 수 없습니다")
    private Integer stockQuantity;

    @NotNull(message = "카테고리를 선택해주세요")
    private Long categoryId;

    private String categoryName;
    private String categorySlug;

    // MultipartFile은 엔티티에는 없지만 파일 업로드를 위해 폼에서만 처리
    @NotNull(message = "이미지를 업로드해주세요")
    private MultipartFile image;

    // 기본 생성자
    public ProductForm() {}

    public ProductForm(Long id, String name, Long price, Integer stockQuantity, Long categoryId, String categoryName, String categorySlug) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categorySlug = categorySlug;
    }

    // 엔티티에서 ProductForm으로 변환하는 메소드
    public static ProductForm from(ProductJpaEntity productJpaEntity) {
        return new ProductForm(
                productJpaEntity.getId(),
                productJpaEntity.getName(),
                productJpaEntity.getPrice(),
                productJpaEntity.getStockQuantity(),
                productJpaEntity.getCategoryJpaEntity().getId(),
                productJpaEntity.getCategoryJpaEntity().getName(),
                productJpaEntity.getCategoryJpaEntity().getSlug()
        );
    }
}
