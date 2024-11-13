package kr.muve.api.config;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

@Configuration
@Profile("local")
@Import(EmbeddedMongoAutoConfiguration.class)
public class EmbeddedMongoConfig {
}