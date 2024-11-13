package kr.muve.api.config;

import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.mongo.transitions.Mongod;
import de.flapdoodle.embed.mongo.transitions.RunningMongodProcess;
import de.flapdoodle.reverse.TransitionWalker;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class EmbeddedMongoConfig {

    private TransitionWalker.ReachedState<RunningMongodProcess> running;

    @PostConstruct
    public void startMongo() throws Exception {
        // Spring 애플리케이션이 시작되기 전에 MongoDB 서버 실행
        running = Mongod.instance().start(Version.Main.V8_0);
        int port = running.current().getServerAddress().getPort();

        System.setProperty("spring.data.mongodb.port", String.valueOf(port));
    }

    // 애플리케이션 종료 시 MongoDB 서버 종료
    @PreDestroy
    public void stopMongo() {
        if (running != null) {
            running.close();
        }
    }
}