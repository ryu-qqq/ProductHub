package com.ryuqq.setof.storage.db.index.product;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductGroupEsRepository extends ElasticsearchRepository<ProductGroupCommandContextDocument, Long> {

}
