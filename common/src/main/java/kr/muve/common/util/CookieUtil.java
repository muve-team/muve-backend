package kr.muve.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {


    /**
     * 쿠키 생성 및 SameSite 속성 포함한 응답 추가
     */
    public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        // 쿠키 생성 및 설정
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);  // JavaScript에서 접근 금지
        cookie.setPath("/");       // 루트 경로에 적용
        cookie.setMaxAge(maxAge); // 7일 유효
        cookie.setSecure(true);    // HTTPS에서만 전송

        // 쿠키를 응답에 추가
        response.addCookie(cookie);

        // SameSite 속성을 헤더에 직접 추가
        response.setHeader("Set-Cookie", String.format(
                "%s=%s; Max-Age=%d; Path=/; HttpOnly; Secure; SameSite=None",
                name, value, maxAge));
    }
}
