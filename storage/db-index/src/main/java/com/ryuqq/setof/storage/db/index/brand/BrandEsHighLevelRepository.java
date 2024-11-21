package com.ryuqq.setof.storage.db.index.brand;


import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.ryuqq.setof.storage.db.index.BulkOperationFactory;
import com.ryuqq.setof.storage.db.index.ElasticsearchCommandTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BrandEsHighLevelRepository {

    private static final String INDEX_NAME = "brand-document";
    private final ElasticsearchCommandTemplate elasticsearchCommandTemplate;

    public BrandEsHighLevelRepository(ElasticsearchCommandTemplate elasticsearchCommandTemplate) {
        this.elasticsearchCommandTemplate = elasticsearchCommandTemplate;
    }

    public void bulkInsert(List<BrandDocument> brandDocuments) {
        List<BulkOperation> operations = BulkOperationFactory.createOperations(
                brandDocuments,
                BrandDocument::getBrandId
        );

        BulkRequest bulkRequest = BulkRequest.of(b -> b.operations(operations));
        elasticsearchCommandTemplate.executeBulk(INDEX_NAME, bulkRequest);
    }

}
