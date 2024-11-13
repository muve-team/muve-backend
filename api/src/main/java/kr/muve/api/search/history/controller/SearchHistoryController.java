package kr.muve.api.search.history.controller;

import kr.muve.common.service.search.history.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search-history")
@RequiredArgsConstructor
@Slf4j
public class SearchHistoryController {

    private final SearchHistoryAdd searchHistoryAdd;
    private final SearchHistoryGet searchHistoryGet;
    private final SearchHistoryRemove searchHistoryRemove;
    private final SearchHistoryClear searchHistoryClear;

    @PostMapping
    public void addSearch(@RequestBody SearchHistoryAddRequest request) {

        String userId = request.userId();
        String keyword = request.keyword();

        log.info("[POST] /search-history, userId: {}, keyword: {}", userId, keyword);

        searchHistoryAdd.add(userId, keyword);
    }

    @GetMapping
    public SearchHistoriesRes getRecentSearches(@RequestParam String userId) {

        log.info("[GET] /search-history, userId: {}", userId);

        return searchHistoryGet.get(userId);
    }

    @DeleteMapping
    public void removeSearch(@RequestBody SearchHistoryRemoveRequest request) {
        String userId = request.userId();
        String keyword = request.keyword();

        log.info("[DELETE] /search-history, userId: {}, keyword: {}", userId, keyword);

        searchHistoryRemove.remove(userId, keyword);
    }

    @DeleteMapping("/all")
    public void clearHistory(@RequestBody SearchHistoryClearRequest request) {

        String userId = request.userId();

        log.info("[DELETE] /search-history/all, userId: {}", userId);

        searchHistoryClear.clear(userId);
    }
}