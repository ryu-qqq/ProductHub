package com.ryuqq.setof.storage.db.index.product;

import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermsQueryField;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.ryuqq.setof.storage.db.index.ElasticsearchQueryTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProductGroupEsQueryRepository implements ProductGroupDocumentQueryRepository{

    private final ElasticsearchQueryTemplate elasticsearchQueryTemplate;

    public ProductGroupEsQueryRepository(ElasticsearchQueryTemplate elasticsearchQueryTemplate) {
        this.elasticsearchQueryTemplate = elasticsearchQueryTemplate;
    }

    @Override
    public List<ProductGroupCommandContextDocument> fetchProductGroupCommandContextDocument(List<Long> productGroupIds) {
        Query termsQuery = Query.of(q -> q.terms(
                t -> t.field("productGroupId")
                        .terms(TermsQueryField.of(tf -> tf.value(
                                productGroupIds.stream()
                                        .map(id -> FieldValue.of(String.valueOf(id)))
                                        .toList()
                        )))
        ));

        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index("product-group-command-context")
                .query(termsQuery)
        );

        SearchResponse<ProductGroupCommandContextDocument> searchResponse =
                elasticsearchQueryTemplate.executeSearch(
                        this.getClass().getSimpleName(),
                        searchRequest,
                        ProductGroupCommandContextDocument.class
                );

        return searchResponse.hits().hits().stream()
                .map(Hit::source)
                .toList();


    }

}
