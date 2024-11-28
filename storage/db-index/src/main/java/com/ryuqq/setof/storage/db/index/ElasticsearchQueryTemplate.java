package com.ryuqq.setof.storage.db.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.CountRequest;
import co.elastic.clients.elasticsearch.core.CountResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import org.springframework.stereotype.Component;

@Component
public class ElasticsearchQueryTemplate  extends ElasticsearchAbstractTemplate {

    public ElasticsearchQueryTemplate(ElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient);
    }

    /**
     * Search 작업 템플릿 메서드
     *
     * @param operationName 작업 이름
     * @param searchRequest SearchRequest 객체
     * @param responseClass 응답 객체 클래스
     * @param <T>           응답 객체 타입
     * @return SearchResponse
     */
    public <T> SearchResponse<T> executeSearch(String operationName, SearchRequest searchRequest, Class<T> responseClass) {
        return execute(operationName, () -> elasticsearchClient.search(searchRequest, responseClass));
    }

    public <T> SearchResponse<T> executeSearch(SearchRequest searchRequest, Class<T> responseClass) {
        return execute("", () -> elasticsearchClient.search(searchRequest, responseClass));
    }

    /**
     * Count 작업 템플릿 메서드
     *
     * @param operationName 작업 이름
     * @param countRequest  CountRequest 객체
     * @return 카운트 결과 값
     */
    public long executeCount(String operationName, CountRequest countRequest) {
        CountResponse countResponse = execute(operationName, () -> elasticsearchClient.count(countRequest));
        return countResponse.count();
    }


}
