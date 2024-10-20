package kr.muve.common.repository.user;

import kr.muve.common.domain.user.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SpringDataUserRepository extends JpaRepository<UserJpaEntity, Long> {
}
