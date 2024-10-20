package kr.muve.common.repository.category;

import kr.muve.common.domain.category.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryJpaEntity, Long> {
}
