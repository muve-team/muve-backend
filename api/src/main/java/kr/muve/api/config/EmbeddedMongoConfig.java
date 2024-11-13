package kr.muve.api.config;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

@Configuration
@ConditionalOnProperty(name = "spring.mongodb.embedded.enabled", havingValue = "true", matchIfMissing = false)
@Import(EmbeddedMongoAutoConfiguration.class)
public class EmbeddedMongoConfig {
}