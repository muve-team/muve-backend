package kr.muve.common.service.user;

import kr.muve.common.domain.user.Address;
import kr.muve.common.domain.user.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserRes {

    private String name;
    private String email;

    public static LoginUserRes from(UserJpaEntity foundUser) {
        return new LoginUserRes(foundUser.getName(), foundUser.getEmail());
    }
}
