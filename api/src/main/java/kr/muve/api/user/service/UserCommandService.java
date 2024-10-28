package kr.muve.api.user.service;

import jakarta.servlet.http.HttpServletResponse;
import kr.muve.api.security.JwtTokenProvider;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.UserNotFoundException;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.user.JoinUser;
import kr.muve.common.service.user.LoginUser;
import kr.muve.common.service.user.UserJoinCommand;
import kr.muve.common.service.user.UserLoginCommand;
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
    public Long join(UserJoinCommand command) {
        // todo : cart 생성하는 로직 만들기
        UserJpaEntity user = UserJpaEntity.createUser(command);
        userRepository.save(user);
        return user.getId();
    }


    @Override
    public String login(UserLoginCommand command, HttpServletResponse response) {
        String email = command.getEmail();
        String password = command.getPassword();

        UserJpaEntity foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("등록되지 않은 회원입니다."));

        if (!foundUser.validatePassword(password)) {
            throw new UserNotFoundException("회원 정보가 올바르지 않습니다.");
        }

        String token = jwtTokenProvider.createToken(email);
//        cookieUtil.addCookie(response, "authToken", token, 7 * 24 * 60 * 60);

        return token;
    }
}
