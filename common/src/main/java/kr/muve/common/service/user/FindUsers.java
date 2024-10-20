package kr.muve.common.service.user;

import kr.muve.common.domain.user.UserJpaEntity;
import java.util.List;

public interface FindUsers {

    UserJpaEntity findById(Long id);
    List<UserJpaEntity> findUsers();
}
