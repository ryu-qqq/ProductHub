package com.ryuqq.setof.storage.db.index.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.URI;
import java.util.List;
import java.util.stream.Stream;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.ryuqq.setof.storage.db.index")
public class ElasticsearchConfig {

    private final ElasticsearchProperties elasticsearchProperties;

    public ElasticsearchConfig(ElasticsearchProperties elasticsearchProperties) {
        this.elasticsearchProperties = elasticsearchProperties;
    }

    @Bean
    public ElasticsearchClient elasticsearchClient() {

        List<HttpHost> hosts = Stream.of(elasticsearchProperties.getUris().split(","))
                .map(URI::create)
                .map(uri -> new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme()))
                .toList();

        RestClient restClient = RestClient.builder(hosts.toArray(new HttpHost[0])).build();

        RestClientTransport transport = new RestClientTransport(
                restClient,
                new JacksonJsonpMapper()
        );

        return new ElasticsearchClient(transport);
    }

}