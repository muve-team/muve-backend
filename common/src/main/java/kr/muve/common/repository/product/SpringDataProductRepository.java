package kr.muve.common.repository.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<ProductJpaEntity, Long> {
}
