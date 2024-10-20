package kr.muve.common.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryForm {

    private Long id;
    @NotBlank(message = "카테고리 한글명을 작성해주세요")
    @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력할 수 있습니다.")
    private String koreanName;
    @NotBlank(message = "카테고리 영어명을 작성해주세요")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "영어 알파벳만 입력할 수 있습니다.")
    private String englishName;

    public CategoryForm() {
    }

    public static CategoryForm categoryForm(Long id, String koreanName, String englishName ) {
        return new CategoryForm(id, koreanName, englishName);
    }
}