package com.ryuqq.setof.cache.core.brand;

import java.time.Duration;
import java.util.Optional;

public interface BrandCacheRepository {

    void saveBrand(long brandId, BrandCacheDto brandCacheDto, Duration ttl);
    Optional<BrandCacheDto> fetchById(long brandId);
    void deleteBrand(long brandId);
    boolean existsBrand(long brandId);

}

