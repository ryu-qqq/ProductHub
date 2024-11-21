package com.ryuqq.setof.storage.db.index.brand;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BrandEsRepository extends ElasticsearchRepository<BrandDocument, String> {
}
