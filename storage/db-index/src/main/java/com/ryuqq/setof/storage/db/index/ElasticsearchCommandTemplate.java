package com.ryuqq.setof.storage.db.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchCommandTemplate extends ElasticsearchAbstractTemplate {

    public ElasticsearchCommandTemplate(ElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient);
    }

    /**
     * Bulk 작업 템플릿 메서드
     *
     * @param operationName 작업 이름
     * @param bulkRequest   BulkRequest 객체
     * @return BulkResponse
     */

    public BulkResponse executeBulk(String operationName, BulkRequest bulkRequest) {
        return execute(operationName, () -> elasticsearchClient.bulk(bulkRequest));
    }
}
