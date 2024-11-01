package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TimeDealProductsRes {
    private Long productId;
    private String name;
    private Long price;
    private String imageUrl;

    public static List<TimeDealProductsRes> from(List<ProductJpaEntity> products) {
        return products.stream()
                .map(p -> new TimeDealProductsRes(p.getId(), p.getName(), p.getPrice(), p.getImageUrl()))
                .toList();
    }
}
