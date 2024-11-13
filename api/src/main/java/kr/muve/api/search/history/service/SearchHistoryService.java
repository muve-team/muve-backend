package kr.muve.api.search.history.service;

import kr.muve.common.repository.search.history.ElasticsearchSearchHistoryCustomRepository;
import kr.muve.common.repository.search.history.ElasticsearchSearchHistoryRepository;
import kr.muve.common.service.search.history.SearchHistoriesRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {

    private final ElasticsearchSearchHistoryRepository searchHistoryRepository;

    /**
     * 검색어 저장 및 카운트 증가
     */
    public void add(String keyword) {
        searchHistoryRepository.addOrIncrementCount(keyword);
    }

    /**
     * 인기 검색어 조회
     */
    public SearchHistoriesRes getHottest(int limit) {
        return SearchHistoriesRes.from(searchHistoryRepository.findHottestSearches(limit));
    }

    /**
     * 검색어 자동완성
     */
    public List<String> getAutoComplete(String prefix, int size) {
        return searchHistoryRepository.findAutoCompleteSuggestions(prefix, size);
    }
}