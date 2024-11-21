package com.ryuqq.setof.storage.db.index.product;

import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.ryuqq.setof.storage.db.index.BulkOperationFactory;
import com.ryuqq.setof.storage.db.index.ElasticsearchCommandTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductGroupEsHighLevelRepository {

    private static final String INDEX_NAME = "product-group-command-context";
    private final ElasticsearchCommandTemplate elasticsearchCommandTemplate;

    public ProductGroupEsHighLevelRepository(ElasticsearchCommandTemplate elasticsearchCommandTemplate) {
        this.elasticsearchCommandTemplate = elasticsearchCommandTemplate;
    }

    public void bulkInsert(List<ProductGroupCommandContextDocument> productGroupCommandContextDocuments) {
        List<BulkOperation> operations = BulkOperationFactory.createOperations(
                productGroupCommandContextDocuments,
                ProductGroupCommandContextDocument::getProductGroupId
        );

        BulkRequest bulkRequest = BulkRequest.of(b -> b.operations(operations));

        elasticsearchCommandTemplate.executeBulk(INDEX_NAME, bulkRequest);
    }
}
