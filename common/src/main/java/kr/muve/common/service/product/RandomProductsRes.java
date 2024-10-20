package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RandomProductsRes {
    private Long productId;
    private String name;
    private Long price;

    public static List<RandomProductsRes> from(List<ProductJpaEntity> products) {
        return products.stream()
                .map(p -> new RandomProductsRes(p.getId(), p.getName(), p.getPrice()))
                .toList();
    }
}
