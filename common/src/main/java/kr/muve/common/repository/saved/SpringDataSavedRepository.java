package kr.muve.common.repository.saved;

import kr.muve.common.domain.saved.SavedJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataSavedRepository extends JpaRepository<SavedJpaEntity, Long> {
    @Query(value = "select s from SavedJpaEntity s " +
            "join fetch s.savedProductJpaEntities sp " +
            "join fetch sp.productJpaEntity p " +
            "where s.userJpaEntity.id = :userId")
    SavedJpaEntity findByUserId(@Param("userId") Long id);
}

