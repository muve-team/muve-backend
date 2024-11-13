package kr.muve.common.service.product;

import kr.muve.common.controller.ProductForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String koreanName;
    private String englishName;
    private String brandKoreanName;
    private String brandEnglishName;
    private Long price;
    private Integer stockQuantity;
    private Long categoryId;
    private String categoryName;
    private String categorySlug;
    private MultipartFile image;

    private ProductDto() {}

    public static ProductDto from(ProductForm productForm) {
        return new ProductDto(productForm.getId(), productForm.getKoreanName(), productForm.getEnglishName(),
                productForm.getBrandKoreanName(), productForm.getBrandEnglishName(),
                productForm.getPrice(), productForm.getStockQuantity(),
                productForm.getCategoryId(), productForm.getCategoryName(),
                productForm.getCategorySlug(), productForm.getImage());
    }
}
