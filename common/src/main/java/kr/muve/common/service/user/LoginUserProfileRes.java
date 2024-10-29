package kr.muve.common.service.user;

import kr.muve.common.domain.user.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserProfileRes {

    private String email;
    private String name;
}
