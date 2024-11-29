package com.ryuqq.setof.storage.db.core.site.crawl;

public interface CrawlEndPointPersistenceService {
    long insert(CrawlEndpointEntity crawlEndpointEntity);
    void delete(long crawlMappingId);
}
