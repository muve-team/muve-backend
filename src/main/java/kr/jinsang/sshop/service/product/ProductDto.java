package kr.jinsang.sshop.service.product;

import kr.jinsang.sshop.controller.ProductForm;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String name;
    private Long price;
    private Integer stockQuantity;

    private ProductDto() {}

    public static ProductDto from(ProductForm productForm) {
        return new ProductDto(productForm.getId(), productForm.getName(),
                productForm.getPrice(), productForm.getStockQuantity());
    }
}
