package kr.muve.common.service.search.history;

import kr.muve.common.domain.search.history.SearchHistoryMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public record SearchHistoriesRes(
    List<SearchHistoryRes> histories
) {
    public static SearchHistoriesRes from(List<SearchHistoryMongoEntity> histories) {
        return new SearchHistoriesRes(histories.stream()
                .map(h -> new SearchHistoryRes(h.getId(), h.getUserId(), h.getKeyword(), h.getSearchedAt()))
                .toList());
    }
}
