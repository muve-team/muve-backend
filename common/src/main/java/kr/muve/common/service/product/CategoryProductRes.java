package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryProductRes {
    private Long productId;
    private String koreanName;
    private String englishName;
    private String brandKoreanName;
    private String brandEnglishName;
    private Long price;
    private String imageUrl;
    private Long categoryId;

    public static List<CategoryProductRes> from(List<ProductJpaEntity> product) {
        return product.stream()
                .map(p -> new CategoryProductRes(p.getId(), p.getKoreanName(), p.getEnglishName(),
                        p.getBrandKoreanName(), p.getBrandEnglishName(), p.getPrice(),
                        p.getImageUrl(), p.getCategoryJpaEntity().getId()))
                .toList();
    }
}
