package kr.muve.common.service.search.history;

import kr.muve.common.domain.search.history.SearchHistoryElasticsearchEntity;

import java.util.List;

public record HottestSearchesRes(
        List<HottestSearchRes> keywords
) {
    public static HottestSearchesRes from(List<SearchHistoryElasticsearchEntity> histories) {
        return new HottestSearchesRes(histories.stream()
                .map(h -> new HottestSearchRes(
                        h.getId(),
                        h.getKeyword(),
                        h.getCount()
                ))
                .toList());
    }
}