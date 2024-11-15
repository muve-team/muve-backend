package kr.muve.common.repository.delivery;

import kr.muve.common.domain.category.CategoryJpaEntity;
import kr.muve.common.domain.delivery.DeliveryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataDeliveryRepository extends JpaRepository<DeliveryJpaEntity, Long> {
}
