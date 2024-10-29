package kr.muve.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProfileConfig {

    @Bean
    @Profile("local")
    public Boolean isLocalProfile() {
        return true;
    }

    @Bean
    @Profile("local")
    public Boolean isNotLocalProfile() {
        return false;
    }

    @Bean
    @Profile("dev")
    public Boolean isDevProfile() {
        return true;
    }

    @Bean
    @Profile("prod")
    public Boolean isProdProfile() {
        return true;
    }
}
