package kr.muve.api.security;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.muve.common.exception.BaseException;
import kr.muve.common.exception.ErrorCode;
import kr.muve.common.exception.JweException;
import kr.muve.common.security.JweTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class JweTokenInterceptor implements HandlerInterceptor {

    private final JweTokenProvider jweTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String token = jweTokenProvider.resolveToken(request);

        if (StringUtils.isEmpty(token) || !jweTokenProvider.validToken(token)) { // fail fast
            log.error(request.getRequestURI());
            throw new JweException(ErrorCode.JWE_TOKEN_INVALID);
        }

        return true;
    }
}

