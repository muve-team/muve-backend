package kr.muve.common.service.search;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public record SearchProductRes(
        Long productId,
        String koreanName,
        String englishName,
        String brandKoreanName,
        String brandEnglishName,
        Long price,
        Integer stockQuantity,
        String imageUrl,
        String categoryName
) {
    public static List<SearchProductRes> from(List<ProductElasticsearchEntity> product) {
        return product.stream()
                .map(p -> new SearchProductRes(p.getId(), p.getKoreanName(), p.getEnglishName(),
                        p.getBrandKoreanName(), p.getBrandEnglishName(), p.getPrice(), p.getStockQuantity(),
                        p.getImageUrl(), p.getCategoryName()))
                .toList();
    }
}
