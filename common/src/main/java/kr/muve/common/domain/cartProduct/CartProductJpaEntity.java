package kr.muve.common.domain.cartProduct;

import jakarta.persistence.*;
import kr.muve.common.domain.cart.CartJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Getter
@Table(name = "cart_product")
public class CartProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_product_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private CartJpaEntity cartJpaEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductJpaEntity productJpaEntity;

    private Integer count;


    protected CartProductJpaEntity() {}

    private CartProductJpaEntity(CartJpaEntity cart, ProductJpaEntity product, Integer count) {
        this.cartJpaEntity = cart;
        this.productJpaEntity = product;
        this.count = count;
    }

    public static CartProductJpaEntity createCartProduct(CartJpaEntity cart, ProductJpaEntity product, Integer count) {
        Optional<CartProductJpaEntity> existCartProduct = getExistProduct(cart, product);

        // cartproduct에 같은 상품 존재 시, update
        if (existCartProduct.isPresent()) {
            CartProductJpaEntity cartProduct = existCartProduct.get();
            cartProduct.updateCount(count);
            return cartProduct;
        }

        CartProductJpaEntity cartProduct = new CartProductJpaEntity(cart, product, count);
        cart.addCartProduct(cartProduct);
        return cartProduct;
    }

    private static Optional<CartProductJpaEntity> getExistProduct(CartJpaEntity cart, ProductJpaEntity product) {
        Optional<CartProductJpaEntity> existCartProduct = cart.getCartProductJpaEntities().stream()
                .filter(cp -> cp.getProductJpaEntity().getId().equals(product.getId()))
                .findFirst();
        return existCartProduct;
    }

    public void updateCart(CartJpaEntity cart) {
        this.cartJpaEntity = cart;
    }

    // 장바구니 상품 갯수만 변경하는 로직
    public void updateCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartProductJpaEntity that = (CartProductJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
