package kr.muve.common.domain.search.history;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "search_history")
@Getter
public class SearchHistoryMongoEntity {
    @Id
    private String id;
    private String keyword;
    private int count;

    public SearchHistoryMongoEntity(String keyword) {
        this.keyword = keyword;
        this.count = 1;
    }

    public void increaseCount() {
        this.count++;
    }

    public void decreaseCount() {
        if (this.count > 0) {
            this.count--;
        }
    }
}