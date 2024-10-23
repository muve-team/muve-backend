package kr.muve.common.repository.product;

import kr.muve.common.domain.product.ProductJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataProductRepository extends JpaRepository<ProductJpaEntity, Long> {
    @Query(value = "select p from ProductJpaEntity p order by RANDOM()")
    List<ProductJpaEntity> findRandomProducts(Pageable pageable);

    @Query(value = "select p from ProductJpaEntity p where p.categoryJpaEntity.id = :categoryId")
    List<ProductJpaEntity> findByCategoryIdWithPage(@Param("categoryId") Long categoryId, Pageable pageable);

    @Query(value = "select p from ProductJpaEntity p where p.name like %:keyword%")
    List<ProductJpaEntity> findAllWithKeyword(@Param("keyword") String keyword, Pageable pageable);

}
