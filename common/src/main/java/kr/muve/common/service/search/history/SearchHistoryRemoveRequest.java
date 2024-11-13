package kr.muve.common.service.search.history;

public record SearchHistoryRemoveRequest(
        String userId,
        String keyword
) {}
