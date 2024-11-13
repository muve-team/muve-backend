package kr.muve.api.search.history.service;

import kr.muve.common.domain.search.history.SearchHistoryMongoEntity;
import kr.muve.common.repository.search.history.MongoSearchHistoryRepository;
import kr.muve.common.service.search.history.SearchHistoriesRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {

    private final MongoSearchHistoryRepository searchHistoryRepository;

    /**
     * 검색어 저장 및 카운트 증가
     */
    public void add(String keyword) {
        SearchHistoryMongoEntity existingHistory = searchHistoryRepository.findByKeyword(keyword);

        if (existingHistory != null) {
            // 기존 검색어가 있으면 카운트 증가
            existingHistory.increaseCount();
            searchHistoryRepository.save(existingHistory);
        } else {
            // 새 검색어 추가
            searchHistoryRepository.save(new SearchHistoryMongoEntity(keyword));
        }
    }

    /**
     * 인기 검색어 조회 (count 기준 정렬)
     */
    public SearchHistoriesRes get(int limit) {
        return SearchHistoriesRes.from(searchHistoryRepository
                .findAllByOrderByCountDesc(PageRequest.of(0, limit)));
    }

    /**
     * 특정 검색어 삭제
     */
    public void remove(String keyword) {
        SearchHistoryMongoEntity history = searchHistoryRepository.findByKeyword(keyword);
        if (history != null) {
            if (history.getCount() > 1) {
                // 카운트가 1보다 크면 카운트만 감소
                history.decreaseCount();
                searchHistoryRepository.save(history);
            } else {
                // 카운트가 1이면 검색어 삭제
                searchHistoryRepository.delete(history);
            }
        }
    }

    /**
     * 모든 검색어 삭제
     */
    public void clear() {
        searchHistoryRepository.deleteAll();
    }
}