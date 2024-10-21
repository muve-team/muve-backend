package kr.muve.api.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 모든 경로에 대해
                        .allowedOrigins("http://localhost:3000")  // 허용할 출처(origin)
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // 허용할 HTTP 메소드
                        .allowedHeaders("*")  // 허용할 헤더
                        .allowCredentials(true);  // 인증 정보를 허용할지 여부 (쿠키, 인증 토큰 등)
            }
        };
    }
}
