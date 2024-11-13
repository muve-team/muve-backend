package kr.muve.common.service.cart;

import kr.muve.common.domain.cart.product.CartProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartProducts {

    private Long cartProductId;
    private String name;
    private Long price;
    private Integer count;
    private String imageUrl;
    private String categoryName;

    public static List<CartProducts> from(List<CartProductJpaEntity> cartProducts) {
        return cartProducts.stream()
                .map(p -> new CartProducts(p.getId(), p.getProductJpaEntity().getName(),
                        p.getProductJpaEntity().getPrice(),
                        p.getCount(), p.getProductJpaEntity().getImageUrl(),
                        p.getProductJpaEntity().getCategoryJpaEntity().getName()))
                .toList();

    }
}
