package kr.muve.common.repository.search.history;

import kr.muve.common.domain.search.history.SearchHistoryMongoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MongoSearchHistoryRepository extends MongoRepository<SearchHistoryMongoEntity, String> {
    SearchHistoryMongoEntity findByKeyword(String keyword);
    List<SearchHistoryMongoEntity> findAllByOrderByCountDesc(Pageable pageable);
    void deleteByKeyword(String keyword);
    boolean existsByKeyword(String keyword);
}