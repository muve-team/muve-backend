package kr.muve;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "kr.muve.common.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Data.*"))
@EntityScan(basePackages = "kr.muve.common.domain")
@SpringBootApplication(scanBasePackages = "kr.muve")
public class MallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }
}