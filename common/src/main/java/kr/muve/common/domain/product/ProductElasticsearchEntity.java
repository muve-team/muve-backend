package kr.muve.common.domain.product;

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
    private String name;

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

}
