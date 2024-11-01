package kr.muve.admin.config;

import kr.muve.common.security.JwtTokenInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor)  // JWT 인터셉터 등록
                .addPathPatterns("/**")  // 모든 경로에 대해 인터셉터 적용
                .excludePathPatterns("/"); // 로그인 제외
    }
}