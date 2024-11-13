package kr.muve.api.search.history.service;

import kr.muve.common.domain.search.history.SearchHistoryMongoEntity;
import kr.muve.common.repository.search.history.MongoSearchHistoryRepository;
import kr.muve.common.service.search.history.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchHistoryService implements SearchHistoryAdd, SearchHistoryGet, SearchHistoryRemove, SearchHistoryClear {

    private final MongoSearchHistoryRepository searchHistoryRepository;
    private static final int MAX_SEARCH_HISTORY = 5;

    /**
     * 검색어 저장
     */
    @Override
    public void add(String userId, String keyword) {
        // 중복 검색어 처리
        if (searchHistoryRepository.existsByUserIdAndKeyword(userId, keyword)) {
            searchHistoryRepository.deleteByUserIdAndKeyword(userId, keyword);
        }

        // 새 검색어 추가
        searchHistoryRepository.save(new SearchHistoryMongoEntity(userId, keyword));

        // 최대 개수 초과시 가장 오래된 검색어 삭제
        List<SearchHistoryMongoEntity> histories = searchHistoryRepository
                .findByUserIdOrderBySearchedAtDesc(userId, PageRequest.of(0, MAX_SEARCH_HISTORY + 1));

        if (histories.size() > MAX_SEARCH_HISTORY) {
            SearchHistoryMongoEntity oldest = histories.get(histories.size() - 1);
            searchHistoryRepository.delete(oldest);
        }
    }

    /**
     * 최근 검색어 조회
     */
    @Override
    public SearchHistoriesRes get(String userId) {
        return SearchHistoriesRes.from(searchHistoryRepository
                .findByUserIdOrderBySearchedAtDesc(userId, PageRequest.of(0, MAX_SEARCH_HISTORY)));
    }

    /**
     * 특정 검색어 삭제
     */
    @Override
    public void remove(String userId, String keyword) {
        searchHistoryRepository.deleteByUserIdAndKeyword(userId, keyword);
    }

    /**
     * 사용자의 모든 검색어 삭제
     */
    @Override
    public void clear(String userId) {
        List<SearchHistoryMongoEntity> histories = searchHistoryRepository
                .findByUserIdOrderBySearchedAtDesc(userId, PageRequest.of(0, Integer.MAX_VALUE));
        searchHistoryRepository.deleteAll(histories);
    }
}