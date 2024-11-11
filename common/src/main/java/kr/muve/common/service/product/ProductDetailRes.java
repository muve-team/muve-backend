package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailRes {
    private Long productId;
    private String name;
    private String brandName;
    private Long price;
    private String imageUrl;
    private Integer stockQuantity;
    private String categoryName;
    private String categorySlug;

    public static ProductDetailRes from(ProductJpaEntity product) {
        return new ProductDetailRes(
                product.getId(),
                product.getName(),
                product.getBrandName(),
                product.getPrice(),
                product.getImageUrl(),
                product.getStockQuantity(),
                product.getCategoryJpaEntity().getName(),
                product.getCategoryJpaEntity().getSlug()
        );
    }
}