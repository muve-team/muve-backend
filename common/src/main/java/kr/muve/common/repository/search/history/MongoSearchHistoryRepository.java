package kr.muve.common.repository.search.history;

import kr.muve.common.domain.search.history.SearchHistoryMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MongoSearchHistoryRepository extends MongoRepository<SearchHistoryMongoEntity, String> {
    List<SearchHistoryMongoEntity> findByUserIdOrderBySearchedAtDesc(String userId, Pageable pageable);
    void deleteByUserIdAndKeyword(String userId, String keyword);
    boolean existsByUserIdAndKeyword(String userId, String keyword);
}