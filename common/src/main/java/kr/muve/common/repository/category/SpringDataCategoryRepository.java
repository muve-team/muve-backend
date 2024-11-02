package kr.muve.common.repository.category;

import kr.muve.common.domain.category.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryJpaEntity, Long> {
    @Query("select c from CategoryJpaEntity c where c.parent.id is null")
    List<CategoryJpaEntity> findParentCategories();
}
