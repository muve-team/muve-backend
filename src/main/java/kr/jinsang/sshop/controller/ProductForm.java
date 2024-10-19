package kr.jinsang.sshop.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import kr.jinsang.sshop.domain.category.Category;
import kr.jinsang.sshop.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductForm {
    private Long id;

    @NotBlank(message = "상품명을 작성해주세요")
    private String name;
    @NotNull(message = "상품 가격을 작성해주세요")
    @Positive(message = "가격은 0일 수 없습니다")
    private Long price;
    @NotNull(message = "상품의 갯수를 작성해주세요")
    @PositiveOrZero(message = "갯수는 음수일 수 없습니다")
    private Integer stockQuantity;
    @NotNull(message = "카테고리를 선택해주세요")
    private Long categoryId;

    private String categoryName;

    public ProductForm() {}

    public static ProductForm from(Product product) {
        return new ProductForm(product.getId(), product.getName(),
                product.getPrice(), product.getStockQuantity(), product.getCategory().getId(),
                product.getCategory().getName());
    }
}
