package kr.muve.common.service.search;

import kr.muve.common.domain.product.ProductElasticsearchEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.service.category.CategoryProductsRes;
import kr.muve.common.service.product.CategoryProductRes;
import org.springframework.data.domain.Page;

import java.util.List;

public record SearchProductsRes(
    List<SearchProductRes> products,
    boolean hasMore,
    Integer total,
    Integer page,
    Integer size
) {
    public static SearchProductsRes from(Page<ProductElasticsearchEntity> page) {
        return new SearchProductsRes(SearchProductRes.from(page.getContent()), page.hasNext(), page.getTotalPages(),
                page.getPageable().getPageNumber(), page.getSize());
    }
}
