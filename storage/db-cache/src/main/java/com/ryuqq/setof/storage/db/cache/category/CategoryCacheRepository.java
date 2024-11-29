package com.ryuqq.setof.storage.db.cache.category;

import java.time.Duration;
import java.util.Optional;

public interface CategoryCacheRepository {
    void saveCategory(long categoryId, CategoryCacheDto categoryCacheDto, Duration ttl);

    Optional<CategoryCacheDto> fetchById(long brandId);

    void deleteCategory(long brandId);

    boolean existsCategory(long brandId);
}
