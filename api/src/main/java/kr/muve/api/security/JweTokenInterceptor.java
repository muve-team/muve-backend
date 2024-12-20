package kr.muve.api.security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.exception.JweException;
import kr.muve.common.security.JweTokenProvider;
import kr.muve.common.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class JweTokenInterceptor implements HandlerInterceptor {

    private final JweTokenProvider jweTokenProvider;
    private final CookieUtil cookieUtil;
    private static final String TOKEN_KEY = "authToken";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String token = jweTokenProvider.resolveToken(request);

        if (!StringUtils.hasText(token) || !jweTokenProvider.validToken(token)) { // fail fast
            log.error(request.getRequestURI());
            throw new JweException(ErrorCode.JWE_TOKEN_INVALID);
        }

        return true;
    }
}

