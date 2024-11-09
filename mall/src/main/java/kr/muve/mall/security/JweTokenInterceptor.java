package kr.muve.mall.security;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.Cookie;
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
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JweTokenInterceptor implements HandlerInterceptor {

    private final JweTokenProvider jweTokenProvider;
    private final CookieUtil cookieUtil;
    private static final String TOKEN_KEY = "authToken";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        try {

            String token = cookieUtil.getCookie(TOKEN_KEY, request);

            if (StringUtils.isEmpty(token) || !jweTokenProvider.validToken(token)) { // fail fast
                response.sendRedirect("/login");
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}

