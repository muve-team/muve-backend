package kr.muve.common.service.search.history;

import kr.muve.common.domain.search.history.SearchHistoryMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

public record SearchHistoryRes(
        String id,
        String keyword,
        int count
) {}