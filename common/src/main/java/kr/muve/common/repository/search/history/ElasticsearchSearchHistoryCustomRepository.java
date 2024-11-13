package kr.muve.common.repository.search.history;

import kr.muve.common.domain.search.history.SearchHistoryElasticsearchEntity;

import java.util.List;

public interface ElasticsearchSearchHistoryCustomRepository {
    void addOrIncrementCount(String keyword);
    List<SearchHistoryElasticsearchEntity> findHottestSearches(int limit);
    List<String> findAutoCompleteSuggestions(String prefix, int size);
}