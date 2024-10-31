package kr.muve.common.service.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewestProductRes {
    private Long productId;
    private String name;
    private Long price;
    private String imageUrl;
    private Long categoryId;

    public static List<NewestProductRes> from(List<ProductJpaEntity> product) {
        return product.stream()
                .map(p -> new NewestProductRes(p.getId(), p.getName(), p.getPrice(),
                        p.getImageUrl(), p.getCategoryJpaEntity().getId()))
                .toList();
    }
}