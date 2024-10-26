package kr.muve.common.repository.cartProduct;

import kr.muve.common.domain.cartProduct.CartProductJpaEntity;
import kr.muve.common.service.cart.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCartProductRepository extends JpaRepository<CartProductJpaEntity, Long> {

}
