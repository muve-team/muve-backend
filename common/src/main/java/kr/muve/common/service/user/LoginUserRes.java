package kr.muve.common.service.user;

import kr.muve.common.domain.user.Address;
import kr.muve.common.domain.user.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserRes {

    private String token;
    private LoginUserProfileRes user;

    public static LoginUserRes from(String token, UserJpaEntity foundUser) {
        return new LoginUserRes(token, new LoginUserProfileRes(foundUser.getName(), foundUser.getEmail()));
    }
}
