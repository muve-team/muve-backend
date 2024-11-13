package kr.muve.common.service.search.history;

public record SearchHistoryRes(
        String id,
        String keyword,
        int count
) {}