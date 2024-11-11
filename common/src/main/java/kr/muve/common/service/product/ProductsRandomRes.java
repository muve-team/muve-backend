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
    private String brandName;
    private Long price;
    private String imageUrl;

    public static List<ProductsRandomRes> from(List<ProductJpaEntity> products) {
        return products.stream()
                .map(p -> new ProductsRandomRes(p.getId(), p.getName(), p.getBrandName(), p.getPrice(), p.getImageUrl()))
                .toList();
    }
}
