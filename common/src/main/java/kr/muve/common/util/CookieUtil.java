package kr.muve.common.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class CookieUtil {

    private final Boolean isNotLocalProfile;

    /**
     * 쿠키 생성 및 SameSite 속성 포함한 응답 추가
     */
    public void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        // 쿠키 생성 및 설정
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        cookie.setSecure(isNotLocalProfile); // HTTPS에서만 전송

        // 쿠키를 응답에 추가
        response.addCookie(cookie);

        // SameSite 속성을 헤더에 직접 추가
        response.setHeader("Set-Cookie", String.format(
                "%s=%s; Max-Age=%d; Path=/; HttpOnly; %s",
                name, value, maxAge, isNotLocalProfile ? "Secure; SameSite=None" : "SameSite=Lax"
        ));
    }

    public String getCookie(String key, HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null; // 쿠키가 없을 경우 null 반환
        }

        return Arrays.stream(request.getCookies())
                .filter(cookie -> key.equals(cookie.getName()))  // 쿠키 이름을 비교
                .findFirst()
                .map(Cookie::getValue)  // 쿠키가 있으면 값 반환
                .orElse(null);  // 쿠키가 없으면 null 반환
    }

    public boolean removeCookie(String key, HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            Arrays.stream(request.getCookies())
                    .filter(cookie -> key.equals(cookie.getName()))  // 삭제할 쿠키 찾기
                    .forEach(cookie -> {
                        cookie.setValue("");       // 쿠키 값 초기화
                        cookie.setPath("/");       // 경로 설정 (쿠키가 설정된 경로와 동일해야 함)
                        cookie.setMaxAge(0);       // 만료 시간 0으로 설정하여 삭제
                        cookie.setHttpOnly(true);  // HttpOnly 설정 유지
                        cookie.setSecure(true);    // Secure 설정 유지 (HTTPS)
                        response.addCookie(cookie);
                    });
            return true;
        }

        return false;
    }
}
