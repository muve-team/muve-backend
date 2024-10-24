package kr.muve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@EnableJpaRepositories(basePackages = "kr.muve.common.repository")
@EntityScan(basePackages = "kr.muve.common.domain")
@SpringBootApplication(scanBasePackages = "kr.muve")
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
