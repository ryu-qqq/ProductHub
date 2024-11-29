package com.ryuqq.setof.storage.db.cache.site;

import com.ryuqq.setof.storage.db.cache.RedissonCacheRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CrawlProductRedissonRepository implements CrawlProductCacheRepository {

    private final RedissonCacheRepository<String> redissonCacheRepository;

    public CrawlProductRedissonRepository(RedissonCacheRepository<String> redissonCacheRepository) {
        this.redissonCacheRepository = redissonCacheRepository;
    }

    @Override
    public void saveCrawlProduct(String key, String value) {
        redissonCacheRepository.save(key, value);
    }

    @Override
    public boolean existByKey(String key) {
        return redissonCacheRepository.exists(key);
    }
}
