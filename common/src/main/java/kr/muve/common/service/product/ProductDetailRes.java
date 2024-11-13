package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailRes {
    private Long productId;
    private String koreanName;
    private String englishName;
    private String brandKoreanName;
    private String brandEnglishName;
    private Long price;
    private String imageUrl;
    private Integer stockQuantity;
    private String categoryName;
    private String categorySlug;

    public static ProductDetailRes from(ProductJpaEntity product) {
        return new ProductDetailRes(
                product.getId(),
                product.getKoreanName(),
                product.getEnglishName(),
                product.getBrandKoreanName(),
                product.getBrandEnglishName(),
                product.getPrice(),
                product.getImageUrl(),
                product.getStockQuantity(),
                product.getCategoryJpaEntity().getName(),
                product.getCategoryJpaEntity().getSlug()
        );
    }
}