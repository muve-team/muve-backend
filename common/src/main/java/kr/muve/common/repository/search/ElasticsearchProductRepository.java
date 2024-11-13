package kr.muve.common.repository.search;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchProductRepository extends ElasticsearchRepository<ProductElasticsearchEntity, Long>,
        ElasticsearchProductCustomRepository {
}