package kr.jinsang.sshop.service.product;

import kr.jinsang.sshop.controller.ProductForm;
import kr.jinsang.sshop.domain.category.Category;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Long price;
    private Integer stockQuantity;
    private Long categoryId;
    private String categoryName;

    private ProductDto() {}

    public static ProductDto from(ProductForm productForm) {
        return new ProductDto(productForm.getId(), productForm.getName(),
                productForm.getPrice(), productForm.getStockQuantity(),
                productForm.getCategoryId(),productForm.getCategoryName());
    }
}
