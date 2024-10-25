package kr.muve.api.security;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.muve.common.exception.JwtTokenInvalidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = jwtTokenProvider.resolveToken(request);

        if (StringUtils.isEmpty(token) || !jwtTokenProvider.isValidToken(token)) { // fail fast
            log.info(request.getRequestURI());
            throw new JwtTokenInvalidException("토큰이 유효하지 않습니다.");
        }

        return true;
    }
}

