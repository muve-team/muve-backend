package kr.muve.common.repository.order;

import kr.muve.common.domain.order.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataOrderRepository extends JpaRepository<OrderJpaEntity, Long>, OrderRepositoryCustom {

}
