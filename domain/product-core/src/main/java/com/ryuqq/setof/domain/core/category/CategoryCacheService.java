package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.storage.db.cache.category.CategoryCacheRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryCacheService {

    private final CategoryCacheRepository categoryCacheRepository;
    private final CategoryMapper categoryMapper;

    public CategoryCacheService(CategoryCacheRepository categoryCacheRepository, CategoryMapper categoryMapper) {
        this.categoryCacheRepository = categoryCacheRepository;
        this.categoryMapper = categoryMapper;
    }


    public Optional<Category> findCategoryFromCache(long categoryId) {
        return categoryCacheRepository.fetchById(categoryId)
                .map(categoryMapper::toDomain);
    }

    public void saveCategoryToCache(Category category, Duration ttl) {
        categoryCacheRepository.saveCategory(category.id(), categoryMapper.toCacheDto(category), ttl);
    }


    public void saveCategoriesToCache(List<Category> categories, Duration ttl) {
        categories.forEach(category -> saveCategoryToCache(category, ttl));
    }

}
