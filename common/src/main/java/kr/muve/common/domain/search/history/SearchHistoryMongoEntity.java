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
    private String userId;      // 사용자 ID
    private String keyword;     // 검색어
    private LocalDateTime searchedAt; // 검색 시간

    public SearchHistoryMongoEntity(String userId, String keyword) {
        this.userId = userId;
        this.keyword = keyword;
        this.searchedAt = LocalDateTime.now();
    }

    // getter, setter
}
