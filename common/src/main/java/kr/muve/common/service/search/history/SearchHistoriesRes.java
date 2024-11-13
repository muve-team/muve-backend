package kr.muve.common.service.search.history;

import kr.muve.common.domain.search.history.SearchHistoryElasticsearchEntity;

import java.util.List;

public record SearchHistoriesRes(
        List<SearchHistoryRes> histories
) {
    public static SearchHistoriesRes from(List<SearchHistoryElasticsearchEntity> histories) {
        return new SearchHistoriesRes(histories.stream()
                .map(h -> new SearchHistoryRes(
                        h.getId(),
                        h.getKeyword(),
                        h.getCount()
                ))
                .toList());
    }
}