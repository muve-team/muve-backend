package kr.muve.common.domain.product;

import kr.muve.common.service.product.ProductDto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;

@Document(indexName = "product")
@Setting(settingPath = "es-settings.json")
@Getter
public class ProductElasticsearchEntity {

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "default_analyzer")
    private String koreanName;

    @Field(type = FieldType.Text, analyzer = "default_analyzer")
    private String englishName;

    @Field(type = FieldType.Text, analyzer = "default_analyzer")
    private String brandKoreanName;

    @Field(type = FieldType.Text, analyzer = "default_analyzer")
    private String brandEnglishName;

    @Field(type = FieldType.Text, analyzer = "default_analyzer")
    private String categoryName;

    @Field(type = FieldType.Long)
    private Long price;

    @Field(type = FieldType.Integer)
    private Integer stockQuantity;

    @Field(type = FieldType.Text, index = false)
    private String imageUrl;

    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDate;

    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedDate;

    protected ProductElasticsearchEntity() {
    }

    public ProductElasticsearchEntity(Long id, String koreanName, String englishName, String brandKoreanName, String brandEnglishName,
                                      String categoryName, Long price, Integer stockQuantity,
                                      String imageUrl, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.brandKoreanName = brandKoreanName;
        this.brandEnglishName = brandEnglishName;
        this.categoryName = categoryName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public static ProductElasticsearchEntity from(ProductJpaEntity entity) {
        return new ProductElasticsearchEntity(
                entity.getId(),
                entity.getKoreanName(),
                entity.getEnglishName(),
                entity.getBrandKoreanName(),
                entity.getBrandEnglishName(),
                entity.getCategoryJpaEntity().getName(),
                entity.getPrice(),
                entity.getStockQuantity(),
                entity.getImageUrl(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
