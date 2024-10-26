package kr.muve.common.repository.cart;

import kr.muve.common.domain.cart.CartJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataCartRepository extends JpaRepository<CartJpaEntity, Long> {
    @Query(value = "select c from CartJpaEntity c " +
            "left join fetch c.cartProductJpaEntities cp " +
            "left join fetch cp.productJpaEntity p " +
            "where c.userJpaEntity.id = :userId")
    CartJpaEntity findByUserId(@Param("userId") Long id);
}

