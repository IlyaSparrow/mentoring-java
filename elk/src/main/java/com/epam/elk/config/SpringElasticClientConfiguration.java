package com.epam.elk.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.epam.elk.repository")
public class SpringElasticClientConfiguration {

  private static final String URL = "localhos:9200";

  @Bean
  public RestHighLevelClient client() {
    ClientConfiguration configuration = ClientConfiguration.builder()
        .connectedTo(URL)
        .build();

    return RestClients.create(configuration).rest();
  }

  @Bean
  public ElasticsearchOperations elasticsearchOperations() {
    return new ElasticsearchRestTemplate(client());
  }
}
