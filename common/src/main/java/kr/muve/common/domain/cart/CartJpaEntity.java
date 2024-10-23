package kr.muve.common.domain.cart;

import jakarta.persistence.*;
import kr.muve.common.domain.cartProduct.CartProductJpaEntity;
import kr.muve.common.domain.product.ProductJpaEntity;
import kr.muve.common.domain.user.UserJpaEntity;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
public class CartJpaEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserJpaEntity userJpaEntity;

    @OneToMany(mappedBy = "cartJpaEntity", cascade = CascadeType.PERSIST)
    private List<CartProductJpaEntity> cartProductJpaEntities = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartJpaEntity that = (CartJpaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
