package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class TimeDealProductsRes {
    private Long productId;
    private String name;
    private Long price;
    private String imageUrl;
    private Long categoryId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public static List<TimeDealProductsRes> from(List<ProductJpaEntity> products) {
        return products.stream()
                .map(p -> new TimeDealProductsRes(p.getId(), p.getName(), p.getPrice(), p.getImageUrl(),
                        p.getCategoryJpaEntity().getId(), p.getTimeDealJpaEntity().getStartAt(), p.getTimeDealJpaEntity().getEndAt()))
                .toList();
    }
}
