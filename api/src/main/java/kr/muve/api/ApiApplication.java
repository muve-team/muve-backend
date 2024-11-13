package kr.muve.api;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import kr.muve.common.repository.product.ElasticsearchProductRepository;
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
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EntityScan(basePackages = "kr.muve.common.domain")
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = { "kr.muve.common", "kr.muve.api"}, exclude = {
        MongoHealthContributorAutoConfiguration.class,
        ElasticsearchRestHealthContributorAutoConfiguration.class,
        EmbeddedMongoAutoConfiguration.class
})
@EnableJpaRepositories(basePackages = "kr.muve.common.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {JpaRepository.class}))
@EnableElasticsearchRepositories(basePackages = "kr.muve.common.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {ElasticsearchRepository.class}))
@EnableMongoRepositories(basePackages = "kr.muve.common.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {MongoRepository.class}))
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
