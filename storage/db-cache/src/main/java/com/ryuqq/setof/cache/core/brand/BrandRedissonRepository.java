package com.ryuqq.setof.cache.core.brand;

import com.ryuqq.setof.cache.core.RedissonCacheRepository;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.Optional;

@Repository
public class BrandRedissonRepository implements BrandCacheRepository {

    private final RedissonCacheRepository<BrandCacheDto> redissonCacheRepository;

    public BrandRedissonRepository(RedissonCacheRepository<BrandCacheDto> redissonCacheRepository) {
        this.redissonCacheRepository = redissonCacheRepository;
    }

    @Override
    public void saveBrand(long brandId, BrandCacheDto brandCacheDto, Duration ttl) {
        String key = generateKey(brandId);
        redissonCacheRepository.save(key, brandCacheDto, ttl);
    }

    @Override
    public Optional<BrandCacheDto> fetchById(long brandId) {
        String key = generateKey(brandId);
        return Optional.ofNullable(redissonCacheRepository.find(key));
    }

    @Override
    public void deleteBrand(long brandId) {
        String key = generateKey(brandId);
        redissonCacheRepository.delete(key);
    }

    @Override
    public boolean existsBrand(long brandId) {
        String key = generateKey(brandId);
        return redissonCacheRepository.exists(key);
    }

    private String generateKey(long brandId) {
        return "brand:" + brandId;
    }



}
