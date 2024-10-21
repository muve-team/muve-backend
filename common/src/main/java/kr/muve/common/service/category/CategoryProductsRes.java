package kr.muve.common.service.category;

import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.service.product.ProductListRes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryProductsRes {
    private Long categoryId;
    private String categoryName;
    private String categorySlug;
    private String categoryImageUrl;
    private List<ProductListRes> product;

    public static CategoryProductsRes from(CategoryJpaEntity category, List<ProductListRes> products) {
        return new CategoryProductsRes(category.getId(), category.getName(), category.getSlug(),
                category.getImageUrl(), products);

    }
}
