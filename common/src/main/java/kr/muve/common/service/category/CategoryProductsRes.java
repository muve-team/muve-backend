package kr.muve.common.service.category;

import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.service.product.ProductRes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryProductsRes {
    private Long categoryId;
    private String name;
    private String slug;
    private String imageUrl;
    private List<ProductRes> products;

    public static CategoryProductsRes from(CategoryJpaEntity category, List<ProductRes> products) {
        return new CategoryProductsRes(category.getId(), category.getName(), category.getSlug(),
                category.getImageUrl(), products);

    }
}
