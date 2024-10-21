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
    @NotBlank(message = "카테고리 사진을 등록해주세요")
    @Pattern(regexp = "(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|jpeg|png|gif|bmp)$",
            message = "URL은 유효한 이미지 링크여야 합니다 (jpg, jpeg, png, gif, bmp).")
    private String imageUrl;

    public CategoryForm() {
    }

    public static CategoryForm from(Long id, String name, String slug, String imageUrl ) {
        return new CategoryForm(id, name, slug, imageUrl);
    }
}