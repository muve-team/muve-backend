package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductListRes {
    private Long productId;
    private String name;
    private Long price;
    private Integer count;
    private String imageUrl;

    public static List<ProductListRes> from(List<ProductJpaEntity> product) {
        return product.stream()
                .map(p -> new ProductListRes(p.getId(), p.getName(), p.getPrice(), p.getStockQuantity(), p.getImageUrl()))
                .toList();

    }

}
