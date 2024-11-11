package kr.muve.admin.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import kr.muve.common.domain.product.ProductElasticsearchEntity;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.*;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

@Configuration
@Profile("local")
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    private static final String ELASTICSEARCH_IMAGE = "docker.elastic.co/elasticsearch/elasticsearch:8.5.1";
    private ElasticsearchContainer container;

    @PostConstruct
    public void startContainerAndSetProperties() {
        container = new ElasticsearchContainer(ELASTICSEARCH_IMAGE)
                .withEnv("discovery.type", "single-node")
                .withEnv("xpack.security.enabled", "false")
                .withEnv("ES_JAVA_OPTS", "-Xms512m -Xmx512m");
        container.start();
    }

    @PreDestroy
    public void stopContainer() {
        if (container != null) {
            container.stop();
        }
    }

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(container.getHttpHostAddress())
                .withConnectTimeout(5000)
                .withSocketTimeout(3000)
                .build();
    }

    @Bean
    public ElasticsearchContainer elasticsearchContainer() {
        return container;
    }

    @Bean
    public ApplicationRunner initializeElasticsearch(ElasticsearchOperations elasticsearchOperations) {
        return args -> {
            try {
                IndexOperations indexOps = elasticsearchOperations.indexOps(ProductElasticsearchEntity.class);
                if (!indexOps.exists()) {
                    // Create settings with analyzer configuration
                    Document settings = Document.create();
                    settings.put("analysis", createAnalysisSettings());

                    // Create index with settings
                    indexOps.create(settings);

                    // Apply mapping
                    indexOps.putMapping(indexOps.createMapping());
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to initialize Elasticsearch indices", e);
            }
        };
    }

    private Document createAnalysisSettings() {
        Document analysis = Document.create();
        Document analyzer = Document.create();

        // Configure standard analyzer for local environment
        Document standardAnalyzer = Document.create();
        standardAnalyzer.put("type", "standard");
        analyzer.put("default_analyzer", standardAnalyzer);

        analysis.put("analyzer", analyzer);
        return analysis;
    }
}