package com.ryuqq.setof.cache.core.site;

public interface CrawlProductCacheRepository {

    void saveCrawlProduct(String key, String value);
    boolean existByKey(String key);

}
