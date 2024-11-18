package com.ryuqq.setof.storage.db.index.product;

import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductGroupEsQueryRepository implements ProductGroupDocumentQueryRepository{

    private final ElasticsearchTemplate elasticsearchTemplate;

    public ProductGroupEsQueryRepository(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }



}
