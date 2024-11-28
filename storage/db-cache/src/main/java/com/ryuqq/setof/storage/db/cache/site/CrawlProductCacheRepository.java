package com.ryuqq.setof.storage.db.cache.site;

public interface CrawlProductCacheRepository {

    void saveCrawlProduct(String key, String value);
    boolean existByKey(String key);

}
