package com.ryuqq.setof.storage.db.cache;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedissonCacheService<T> {

    private final RedissonClient redissonClient;

    public RedissonCacheService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }


    public void save(String key, T value) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }


    public void save(String key, T value, long timeout, TimeUnit unit) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(value, timeout, unit);
    }


    public T find(String key) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }


    public void delete(String key) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.delete();
    }


    public boolean exists(String key) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket.isExists();
    }
}
