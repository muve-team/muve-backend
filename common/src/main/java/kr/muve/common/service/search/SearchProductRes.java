package kr.muve.common.service.search;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public record SearchProductRes(
        Long productId,
        String name,
        Long price,
        Integer stockQuantity,
        String imageUrl,
        String categoryName
) {
    public static List<SearchProductRes> from(List<ProductElasticsearchEntity> product) {
        return product.stream()
                .map(p -> new SearchProductRes(p.getId(), p.getName(), p.getPrice(), p.getStockQuantity(),
                        p.getImageUrl(), p.getCategoryName()))
                .toList();
    }
}
