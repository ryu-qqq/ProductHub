package com.ryuqq.setof.storage.db.core.site.crawl;

import java.util.List;

public interface CrawlProductPersistenceService {

    void batchInsertCrawlProducts(List<CrawlProductEntity> crawlProductEntities);
}
