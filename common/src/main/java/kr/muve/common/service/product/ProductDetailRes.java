package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailRes {
    private Long productId;
    private String name;
    private Long price;
    private Integer stockQuantity;
    private String categoryKoreanName;
    private String categoryEnglishName;

    public static ProductDetailRes from(ProductJpaEntity product) {
        return new ProductDetailRes(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategoryJpaEntity().getKoreanName(),
                product.getCategoryJpaEntity().getEnglishName()
        );
    }
}