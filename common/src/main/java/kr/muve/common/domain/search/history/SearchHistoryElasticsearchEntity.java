package kr.muve.common.domain.search.history;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "search_history")
@Getter
public class SearchHistoryElasticsearchEntity {
    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "default_analyzer") // 한글 분석기 사용
    private String keyword;

    @Field(type = FieldType.Integer)
    private int count;

    public SearchHistoryElasticsearchEntity(String keyword) {
        this.keyword = keyword;
        this.count = 1;
    }

    public void incrementCount() {
        this.count++;
    }
}