package kr.muve;

import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.data.mongo.MongoHealthContributorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.elasticsearch.ElasticsearchRestHealthContributorAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "kr.muve.common.domain")
@SpringBootApplication(scanBasePackages = { "kr.muve.common", "kr.muve.mall"}, exclude = {
        MongoHealthContributorAutoConfiguration.class,
        ElasticsearchRestHealthContributorAutoConfiguration.class
})
@EnableJpaRepositories(basePackages = "kr.muve.common.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {JpaRepository.class}))
@EnableElasticsearchRepositories(basePackages = "kr.muve.common.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ElasticsearchRepository.class}))
public class MallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }
}