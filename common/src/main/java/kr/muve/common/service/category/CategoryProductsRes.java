package kr.muve.common.service.category;

import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.service.product.CategoryProductRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryProductsRes {
    private List<CategoryProductRes> products;
    private boolean hasMore;
    private Integer total;
    private Integer page;
    private Integer size;

    public static CategoryProductsRes from(Page<ProductJpaEntity> page) {
        return new CategoryProductsRes(CategoryProductRes.from(page.getContent()), page.hasNext(), page.getTotalPages(),
                page.getPageable().getPageNumber(), page.getSize());
    }
}
