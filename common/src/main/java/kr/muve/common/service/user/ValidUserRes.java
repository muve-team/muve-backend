package kr.muve.common.service.user;

import kr.muve.common.domain.user.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@AllArgsConstructor
public class ValidUserRes {

    private String token;
    private ValidUserProfileRes user;

    public static ValidUserRes from(String token, UserJpaEntity foundUser) {
        if (ObjectUtils.isEmpty(token) && ObjectUtils.isEmpty(foundUser)) {
            return new ValidUserRes(null, new ValidUserProfileRes(null, null));
        }

        return new ValidUserRes(token, new ValidUserProfileRes(foundUser.getName(), foundUser.getEmail()));
    }
}
