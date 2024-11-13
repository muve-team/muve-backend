package kr.muve.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableJpaRepositories(basePackages = "kr.muve.common.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Data.*"))
@EntityScan(basePackages = "kr.muve.common.domain")
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "kr.muve")
@EnableElasticsearchRepositories(basePackages = "kr.muve.common.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Elastic.*"))
@EnableMongoRepositories(basePackages = "kr.muve.common.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*Mongo.*"))
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
