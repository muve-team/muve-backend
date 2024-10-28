package kr.muve.api.user.service;

import jakarta.servlet.http.HttpServletResponse;
import kr.muve.api.security.JwtTokenProvider;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.exception.UserNotFoundException;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.user.*;
import kr.muve.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserCommandService implements JoinUser, LoginUser {

    private final SpringDataUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final CookieUtil cookieUtil;

    @Override
    public JoinUserRes join(UserJoinCommand command) {
        // todo : cart 생성하는 로직 만들기
        UserJpaEntity user = UserJpaEntity.createUser(command);
        userRepository.save(user);
        return JoinUserRes.from(user);
    }


    @Override
    public LoginUserRes login(UserLoginCommand command, HttpServletResponse response) {
        String email = command.getEmail();
        String password = command.getPassword();

        UserJpaEntity foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (!foundUser.validatePassword(password)) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        String token = jwtTokenProvider.createToken(email);
        cookieUtil.addCookie(response, "authToken", token, 7 * 24 * 60 * 60);

        return LoginUserRes.from(foundUser);
    }
}
