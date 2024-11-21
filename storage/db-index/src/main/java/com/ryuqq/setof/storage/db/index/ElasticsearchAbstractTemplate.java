package com.ryuqq.setof.storage.db.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ElasticsearchAbstractTemplate {

    protected final ElasticsearchClient elasticsearchClient;
    private final Logger log = LoggerFactory.getLogger(getClass());

    protected ElasticsearchAbstractTemplate(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    /**
     * Elasticsearch 작업을 실행하는 템플릿 메서드
     *
     * @param operationName 작업 이름 (로깅용)
     * @param action        실행할 작업
     * @param <T>           작업 결과 타입
     * @return 작업 결과
     */
    protected <T> T execute(String operationName, ElasticsearchAction<T> action) {
        try {
            log.info("Starting Elasticsearch operation: {}", operationName);
            T result = action.perform();
            log.info("Elasticsearch operation '{}' completed successfully", operationName);
            return result;
        } catch (Exception e) {
            log.error("Error during Elasticsearch operation '{}': {}", operationName, e.getMessage(), e);
            throw new RuntimeException("Failed to execute Elasticsearch operation: " + operationName, e);
        }
    }

}
