package kr.muve.common.repository.cart.product;

import kr.muve.common.domain.cart.product.CartProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCartProductRepository extends JpaRepository<CartProductJpaEntity, Long> {

}
