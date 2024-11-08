package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.cache.RedissonCacheService;
import org.springframework.stereotype.Service;

@Service
public class CrawlProductCacheService {

    private final RedissonCacheService<String> redissonCacheService;

    public CrawlProductCacheService(RedissonCacheService<String> redissonCacheService) {
        this.redissonCacheService = redissonCacheService;
    }

    public void saveProduct(String key, String value) {
        redissonCacheService.save(key, value);
    }

    public boolean productExists(String key) {
        return redissonCacheService.exists(key);
    }

}
