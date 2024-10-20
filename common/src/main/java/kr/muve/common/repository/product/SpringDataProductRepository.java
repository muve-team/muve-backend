package kr.muve.common.repository.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataProductRepository extends JpaRepository<ProductJpaEntity, Long> {
    @Query(value = "SELECT p FROM ProductJpaEntity p ORDER BY RANDOM()")
    List<ProductJpaEntity> findRandomProducts(Pageable pageable);
}
