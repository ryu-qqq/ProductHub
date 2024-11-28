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

    private final static String ID_FIELD = "productGroupId";
    private final static String PRODUCT_DOCUMENT = "product-group-command-context";

    public ProductGroupEsQueryRepository(ElasticsearchQueryTemplate elasticsearchQueryTemplate) {
        this.elasticsearchQueryTemplate = elasticsearchQueryTemplate;
    }

    @Override
    public List<ProductGroupCommandContextDocument> fetchByIds(List<Long> productGroupIds) {
        Query termsQuery = Query.of(q -> q.terms(
                t -> t.field(ID_FIELD)
                        .terms(TermsQueryField.of(tf -> tf.value(
                                productGroupIds.stream()
                                        .map(id -> FieldValue.of(String.valueOf(id)))
                                        .toList()
                        )))
        ));

        SearchRequest searchRequest = SearchRequest.of(s -> s
                .index(PRODUCT_DOCUMENT)
                .query(termsQuery)
                .size(productGroupIds.size())
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
