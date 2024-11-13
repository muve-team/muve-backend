package kr.muve.common.repository.search;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ElasticsearchProductCustomRepository {
    Page<ProductElasticsearchEntity> searchByKeyword(String keyword, Pageable pageable);
    List<ProductElasticsearchEntity> findSimilarKeywords(String keyword, int size);
}
