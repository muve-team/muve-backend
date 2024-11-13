package kr.muve.common.repository.product;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ElasticsearchProductCustomRepository {
    Page<ProductElasticsearchEntity> searchByKeyword(String keyword, Pageable pageable);
}
