package com.ryuqq.setof.storage.db.core.site.crawl;

public interface CrawlTaskPersistenceService {

    void insert(CrawlTaskEntity crawlTaskEntity);
    void delete(long endpointId);
}
