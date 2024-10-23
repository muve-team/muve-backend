package kr.muve.common.repository.order;

import kr.muve.common.domain.order.OrderJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataOrderRepository extends JpaRepository<OrderJpaEntity, Long>, OrderRepositoryCustom {
    @Query(value = "select o from OrderJpaEntity o where o.userJpaEntity.id = :userId")
    Page<OrderJpaEntity> findAllByUserId(@Param("userId") Long userId, Pageable pageable);

}
