package kr.muve.api.config;

import kr.muve.api.interceptor.CommonHttpRequestInterceptor;
import kr.muve.api.security.JwtTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtTokenInterceptor jwtTokenInterceptor;
    private final CommonHttpRequestInterceptor commonHttpRequestInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 경로에 대해 CORS 적용
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://muve.kr",
                        "https://www.muve.kr"
                )  // 허용할 출처들을 배열로 지정
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메소드
                .allowedHeaders("*")  // 허용할 헤더
                .exposedHeaders("Set-Cookie")  // 응답 헤더 노출
                .allowCredentials(true)  // 인증 정보(Cookie, Authorization 등)를 허용할지 여부
                .maxAge(3600);  // preflight 요청 캐시 시간

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor)  // JWT 인터셉터 등록
                .addPathPatterns("/**")  // 모든 경로에 대해 인터셉터 적용
                .excludePathPatterns(
                        "/",
                        "/user/join",
                        "/user/login", "/error",
                        "/user/valid",
                        "/products/newest",
                        "/actuator/health",
                        "/products/random",
                        "/product/detail",
                        "/category",
                        "/category/products");  // 특정 경로는 제외
        registry.addInterceptor(commonHttpRequestInterceptor)  // CommonHttpRequestInterceptor 추가
                .addPathPatterns("/**");  // 모든 경로에 적용
    }
}
