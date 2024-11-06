package com.ryuqq.setof.storage.db.core.site;

public interface CrawlMappingPersistenceService {

    long insert(long siteId, long crawlSettingId, long authSettingId);

}
