package kr.muve.common.service.search.history;

public record HottestSearchRes(
        String id,
        String keyword,
        int count
) {}