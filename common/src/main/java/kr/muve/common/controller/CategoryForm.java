package kr.muve.common.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryForm {

    private Long id;
    @NotBlank(message = "카테고리명을 작성해주세요")
    @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력할 수 있습니다.")
    private String name;
    @NotBlank(message = "카테고리 슬로건을 작성해주세요")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "영어 알파벳만 입력할 수 있습니다.")
    private String slug;

    private String imageUrl;

    public CategoryForm() {}

    private CategoryForm(Long id, String name, String slug, String imageUrl) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.imageUrl = imageUrl;
    }

    public static CategoryForm from(Long id, String name, String slug, String imageUrl) {
        return new CategoryForm(id, name, slug, imageUrl);
    }
}