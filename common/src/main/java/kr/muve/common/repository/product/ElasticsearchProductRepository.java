package kr.muve.common.repository.product;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticsearchProductRepository extends ElasticsearchRepository<ProductElasticsearchEntity, Long>,
        ElasticsearchProductCustomRepository {

}