package kr.muve.common.service.user;

import kr.muve.common.domain.user.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinUserRes {

    private String name;
    private String email;

    public static JoinUserRes from(UserJpaEntity foundUser) {
        return new JoinUserRes(foundUser.getName(), foundUser.getEmail());
    }
}
