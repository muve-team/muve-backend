package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.service.category.CategoryProductsRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class NewestProductsRes {
    private List<NewestProductRes> products;
    private boolean hasMore;
    private Integer total;
    private Integer page;
    private Integer size;

    public static NewestProductsRes from(Page<ProductJpaEntity> page) {
        return new NewestProductsRes(NewestProductRes.from(page.getContent()), page.hasNext(), page.getTotalPages(),
                page.getPageable().getPageNumber(), page.getSize());
    }
}
