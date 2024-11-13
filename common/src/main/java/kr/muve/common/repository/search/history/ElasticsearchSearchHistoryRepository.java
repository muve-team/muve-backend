package kr.muve.common.repository.search.history;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import kr.muve.common.domain.search.history.SearchHistoryElasticsearchEntity;
import kr.muve.common.repository.search.ElasticsearchProductCustomRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchSearchHistoryRepository extends ElasticsearchRepository<SearchHistoryElasticsearchEntity, Long>,
        ElasticsearchSearchHistoryCustomRepository {
}