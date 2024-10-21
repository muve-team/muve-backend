package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductsRandomRes {
    private Long productId;
    private String name;
    private Long price;

    public static List<ProductsRandomRes> from(List<ProductJpaEntity> products) {
        return products.stream()
                .map(p -> new ProductsRandomRes(p.getId(), p.getName(), p.getPrice()))
                .toList();
    }
}
