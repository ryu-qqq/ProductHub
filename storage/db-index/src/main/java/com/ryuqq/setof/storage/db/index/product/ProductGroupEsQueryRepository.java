package com.ryuqq.setof.storage.db.index.product;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductGroupEsQueryRepository implements ProductGroupDocumentQueryRepository{

    private final ElasticsearchClient elasticsearchClient;

    public ProductGroupEsQueryRepository(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }


    @Override
    public List<ProductGroupCommandContextDocument> fetchProductGroupCommandContextDocument(List<Long> productGroupIds) {
        try {
            // Terms 쿼리를 생성
            Query termsQuery = Query.of(q -> q.terms(
                    t -> t.field("productGroupId")
                            .terms(TermsQueryField.of(tf -> tf.value(
                                    productGroupIds.stream()
                                            .map(id -> FieldValue.of(String.valueOf(id)))
                                            .toList()
                            )))
            ));

            // 검색 요청 생성
            SearchRequest searchRequest = SearchRequest.of(s -> s
                    .index("product-group-command-context")
                    .query(termsQuery) // 작성한 쿼리 적용
            );

            SearchResponse<ProductGroupCommandContextDocument> searchResponse =
                    elasticsearchClient.search(searchRequest, ProductGroupCommandContextDocument.class);


            return searchResponse.hits().hits().stream()
                    .map(Hit::source)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch ProductGroupCommandContextDocument", e);
        }
    }

}
