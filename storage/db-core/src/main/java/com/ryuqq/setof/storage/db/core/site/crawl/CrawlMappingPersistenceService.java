package com.ryuqq.setof.storage.db.core.site.crawl;

public interface CrawlMappingPersistenceService {

    long insert(long siteId, long crawlSettingId, long authSettingId);

}
