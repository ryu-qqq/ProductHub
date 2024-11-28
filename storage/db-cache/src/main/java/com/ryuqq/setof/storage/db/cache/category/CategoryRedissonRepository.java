package com.ryuqq.setof.storage.db.cache.category;

import com.ryuqq.setof.storage.db.cache.RedissonCacheRepository;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
public class CategoryRedissonRepository implements CategoryCacheRepository{

    private final RedissonCacheRepository<CategoryCacheDto> redissonCacheRepository;

    public CategoryRedissonRepository(RedissonCacheRepository<CategoryCacheDto> redissonCacheRepository) {
        this.redissonCacheRepository = redissonCacheRepository;
    }

    @Override
    public void saveCategory(long categoryId, CategoryCacheDto categoryCacheDto, Duration ttl) {
        String key = generateKey(categoryId);
        redissonCacheRepository.save(key, categoryCacheDto, ttl);
    }

    @Override
    public Optional<CategoryCacheDto> fetchById(long brandId) {
        String key = generateKey(brandId);
        return Optional.ofNullable(redissonCacheRepository.find(key));
    }

    @Override
    public void deleteCategory(long brandId) {
        String key = generateKey(brandId);
        redissonCacheRepository.delete(key);
    }

    @Override
    public boolean existsCategory(long brandId) {
        String key = generateKey(brandId);
        return redissonCacheRepository.exists(key);
    }

    private String generateKey(long categoryId) {
        return "category:" + categoryId;
    }

}
