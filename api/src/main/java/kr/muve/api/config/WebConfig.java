package kr.muve.api.config;

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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 경로에 대해 CORS 적용
                .allowedOrigins(
                        "http://localhost:3000",
                        "https://muve-frontend.netlify.app",
                        "https://main.d3kali48oivsz7.amplifyapp.com/",
                        "https://muve.kr",
                        "https://www.muve.kr"
                )  // 허용할 출처들을 배열로 지정
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메소드
                .allowedHeaders("*")  // 허용할 헤더
                .allowCredentials(true);  // 인증 정보(Cookie, Authorization 등)를 허용할지 여부
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtTokenInterceptor)  // JWT 인터셉터 등록
//                .addPathPatterns("/**")  // 모든 경로에 대해 인터셉터 적용
//                .excludePathPatterns(
//                        "/",
//                        "/user/join",
//                        "/user/login", "/error",
//                        "/actuator/health",
//                        "/product/random",
//                        "/product/detail",
//                        "/category",
//                        "/category/products");  // 특정 경로는 제외
//    }
}
