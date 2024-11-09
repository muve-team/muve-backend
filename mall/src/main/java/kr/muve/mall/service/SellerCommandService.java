package kr.muve.mall.service;

import jakarta.servlet.http.HttpServletResponse;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.security.JweTokenProvider;
import kr.muve.common.service.user.*;
import kr.muve.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static kr.muve.common.security.JweTokenProvider.AUTH_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional
public class SellerCommandService implements LoginSeller {

    private final SpringDataUserRepository userRepository;
    private final JweTokenProvider jweTokenProvider;
    private final CookieUtil cookieUtil;

    @Override
    public void login(SellerLoginCommand command, HttpServletResponse response) {
        String email = command.getEmail();
        String password = command.getPassword();

        UserJpaEntity foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if (!foundUser.validatePassword(password)) {
            throw new BaseException(ErrorCode.USER_NOT_FOUND);
        }

        String token = jweTokenProvider.createToken(email);
        cookieUtil.addCookie(response, AUTH_TOKEN, token, 7 * 24 * 60 * 60);
    }
}
