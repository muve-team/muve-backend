package kr.muve.api.search.history.service;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import kr.muve.common.domain.search.history.SearchHistoryMongoEntity;
import kr.muve.common.repository.search.ElasticsearchProductRepository;
import kr.muve.common.repository.search.history.MongoSearchHistoryRepository;
import kr.muve.common.service.search.history.SearchHistoriesRes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchHistoryService {

    private final MongoSearchHistoryRepository mongoSearchHistoryRepository;
    private final ElasticsearchProductRepository elasticsearchProductRepository;

    /**
     * 검색어 저장 및 카운트 증가
     */
    public void add(String keyword) {
        SearchHistoryMongoEntity existingHistory = mongoSearchHistoryRepository.findByKeyword(keyword);

        if (existingHistory != null) {
            // 기존 검색어가 있으면 카운트 증가
            existingHistory.increaseCount();
            mongoSearchHistoryRepository.save(existingHistory);
        } else {
            // 새 검색어 추가
            mongoSearchHistoryRepository.save(new SearchHistoryMongoEntity(keyword));
        }
    }

    /**
     * 인기 검색어 조회 (count 기준 정렬)
     */
    public SearchHistoriesRes getHottest(int limit) {
        return SearchHistoriesRes.from(mongoSearchHistoryRepository
                .findAllByOrderByCountDesc(PageRequest.of(0, limit)));
    }

    public List<String> getSimilarKeywords(String keyword) {
        return elasticsearchProductRepository.findSimilarKeywords(keyword, 5)
                .stream()
                .map(product -> List.of(
                        product.getKoreanName(),
                        product.getEnglishName(),
                        product.getBrandKoreanName(),
                        product.getBrandEnglishName(),
                        product.getCategoryName()
                ))
                .flatMap(List::stream)
                .filter(StringUtils::hasText) // null이나 빈 문자열 제거
                .distinct() // 중복 제거
                .limit(5) // 최대 5개로 제한
                .toList();
    }

    /**
     * 특정 검색어 삭제
     */
    public void remove(String keyword) {
        SearchHistoryMongoEntity history = mongoSearchHistoryRepository.findByKeyword(keyword);
        if (history != null) {
            if (history.getCount() > 1) {
                // 카운트가 1보다 크면 카운트만 감소
                history.decreaseCount();
                mongoSearchHistoryRepository.save(history);
            } else {
                // 카운트가 1이면 검색어 삭제
                mongoSearchHistoryRepository.delete(history);
            }
        }
    }

    /**
     * 모든 검색어 삭제
     */
    public void clear() {
        mongoSearchHistoryRepository.deleteAll();
    }
}