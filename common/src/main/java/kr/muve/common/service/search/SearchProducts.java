package kr.muve.common.service.search;

import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchProducts {

    private Long productId;
    private String name;
    private Long price;
    private Integer stockQuantity;
    private String imageUrl;
    private String categoryName;
    // private Long savedCount;    // todo : 찜 갯수 넣기

    public static List<SearchProducts> from(List<ProductJpaEntity> products) {
        return products.stream()
                .map(p -> new SearchProducts(p.getId(), p.getName(), p.getPrice(), p.getStockQuantity(),
                        p.getImageUrl(), p.getCategoryJpaEntity().getName()))
                .toList();

    }

}
