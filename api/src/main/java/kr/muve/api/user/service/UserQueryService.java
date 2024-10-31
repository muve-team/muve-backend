package kr.muve.api.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.muve.common.security.JwtTokenProvider;
import kr.muve.common.domain.user.UserJpaEntity;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.repository.user.SpringDataUserRepository;
import kr.muve.common.service.user.*;
import kr.muve.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static kr.muve.common.security.JwtTokenProvider.AUTH_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryService implements ValidUser, LogoutUser {

    private final JwtTokenProvider jwtTokenProvider;
    private final CookieUtil cookieUtil;
    private final SpringDataUserRepository userRepository;

    @Override
    public ValidUserRes valid(HttpServletRequest request) {
        String token = cookieUtil.getCookie(AUTH_TOKEN, request);

        if (!StringUtils.hasText(token)) {
            return ValidUserRes.from(null, null);
        }

        String email = jwtTokenProvider.getSubject(token);

        UserJpaEntity foundUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        return ValidUserRes.from(token, foundUser);
    }

    @Override
    public boolean logout(HttpServletRequest request, HttpServletResponse response) {
        return cookieUtil.removeCookie(AUTH_TOKEN, request, response);
    }
}
