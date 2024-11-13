package kr.muve.common.service.search.history;

public record SearchHistoryAddRequest(
        String userId,
        String keyword
) {}
