package kr.muve.api.config;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile("local")
@Import(EmbeddedMongoAutoConfiguration.class)
public class MongoConfig {
}