package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@Data
@AllArgsConstructor
public class ProductDetailRes {
    private Long productId;
    private String name;
    private Long price;
    private Integer stockQuantity;
    private String cateogryKoreanName;
    private String cateogryEnglishName;

    public static ProductDetailRes from(Optional<ProductJpaEntity> product) {
        return (ProductDetailRes) product.stream()
                .map(p -> new ProductDetailRes(p.getId(), p.getName(), p.getPrice(), p.getStockQuantity(),
                        p.getCategoryJpaEntity().getKoreanName(), p.getCategoryJpaEntity().getEnglishName()));
    }
}
