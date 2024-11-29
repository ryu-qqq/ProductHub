package com.ryuqq.setof.storage.db.core.site.crawl;


import org.springframework.stereotype.Service;

@Service
public class CrawlProductCacheService {



    public void saveProduct(String key, String value) {
        //crawlProductCacheRepository.saveCrawlProduct(key, value);
    }

    public boolean productExists(String key) {
        //return crawlProductCacheRepository.existByKey(key);
        return true;
    }

}
