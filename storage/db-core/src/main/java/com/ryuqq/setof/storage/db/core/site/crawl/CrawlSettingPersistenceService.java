package com.ryuqq.setof.storage.db.core.site.crawl;

public interface CrawlSettingPersistenceService {

    long insert(CrawlSettingEntity crawlSettingEntity);
    void update(long crawlSettingId, CrawlSettingEntity crawlSettingEntity);
}
