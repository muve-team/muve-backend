package kr.muve.common.service.user;

import kr.muve.common.domain.user.User;
import java.util.List;

public interface FindUsers {

    User findOne(Long id);
    List<User> findUsers();
}
