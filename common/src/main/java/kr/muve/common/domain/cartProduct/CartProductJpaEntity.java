package kr.muve.common.domain.cartProduct;

import jakarta.persistence.*;
import kr.muve.common.domain.cart.CartJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class CartProductJpaEntity {

    @Id
    @GeneratedValue
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

    private CartProductJpaEntity(ProductJpaEntity product, Integer count) {
        this.productJpaEntity = product;
        this.count = count;
    }

    public static CartProductJpaEntity createCartProduct(ProductJpaEntity product, Integer count) {
        return new CartProductJpaEntity(product, count);
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
