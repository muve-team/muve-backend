package kr.jinsang.sshop.controller;

import jakarta.validation.constraints.NotBlank;
import kr.jinsang.sshop.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryForm {

    private Long id;
    @NotBlank(message = "카테고리명을 작성해주세요")
    private String name;

    public CategoryForm() {
   }

    public static CategoryForm categoryForm(Long id, String name ) {
        return new CategoryForm(id, name);
    }
}
