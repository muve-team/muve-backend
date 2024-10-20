package kr.muve.common.service.product;

import kr.muve.common.controller.ProductForm;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Long price;
    private Integer stockQuantity;
    private Long categoryId;
    private String categoryKoreanName;
    private String categoryEnglishName;

    private ProductDto() {}

    public static ProductDto from(ProductForm productForm) {
        return new ProductDto(productForm.getId(), productForm.getName(),
                productForm.getPrice(), productForm.getStockQuantity(),
                productForm.getCategoryId(), productForm.getCategoryKoreanName(),
                productForm.getCategoryEnglishName());
    }
}
