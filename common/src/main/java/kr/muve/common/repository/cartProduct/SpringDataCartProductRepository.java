package kr.muve.common.repository.cartProduct;

import kr.muve.common.domain.cart.product.CartProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCartProductRepository extends JpaRepository<CartProductJpaEntity, Long> {

}
