package kr.muve.common.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryForm {

    private Long id;
    @NotBlank(message = "카테고리명을 작성해주세요")
    @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력할 수 있습니다.")
    private String name;
    @NotBlank(message = "카테고리 슬로건을 작성해주세요")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "영어 알파벳만 입력할 수 있습니다.")
    private String slug;

    public CategoryForm() {
    }

    public static CategoryForm from(Long id, String name, String slug ) {
        return new CategoryForm(id, name, slug);
    }
}