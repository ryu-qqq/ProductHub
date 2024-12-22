package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.db.core.category.CategoryQueryRepository;
import com.ryuqq.setof.db.core.category.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CategoryFinder implements CategoryQueryService {

    private final CategoryQueryRepository categoryQueryRepository;
    private final CategoryCacheService categoryCacheService;
    private final CategoryMapper categoryMapper;


    public CategoryFinder(CategoryQueryRepository categoryQueryRepository, CategoryCacheService categoryCacheService, CategoryMapper categoryMapper) {
        this.categoryQueryRepository = categoryQueryRepository;
        this.categoryCacheService = categoryCacheService;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public boolean existById(long categoryId){
        return categoryQueryRepository.existById(categoryId);
    }

    @Override
    public long countByFilter(CategoryFilter categoryFilter){
        return categoryQueryRepository.countByFilter(categoryFilter.toStorageFilter());
    }


    @Override
    public List<Category> fetchCategoriesByFilter(CategoryFilter categoryFilter) {
        return categoryQueryRepository.fetchByFilter(categoryFilter.toStorageFilter()).stream()
                .map(categoryMapper::toDomain)
                .toList();
    }


    @Override
    public List<Category> fetchCategoriesByIds(List<Long> categoryIds){
        Map<Long, Category> cachedCategoryMap = categoryIds.stream()
                .map(id -> Map.entry(id, categoryCacheService.findCategoryFromCache(id)))
                .filter(entry -> entry.getValue().isPresent())
                .collect(
                        Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(), (v1, v2) -> v2)
                );

        List<Long> missingIds = categoryIds.stream()
                .filter(id -> !cachedCategoryMap.containsKey(id))
                .toList();

        List<Category> result = new ArrayList<>(cachedCategoryMap.values());

        if (!missingIds.isEmpty()) {
            CategoryFilter categoryFilter = CategoryFilter.of(missingIds, null, null, null, missingIds.size());
            List<Category> categoriesFromRds = categoryQueryRepository.fetchByFilter(categoryFilter.toStorageFilter())
                    .stream()
                    .map(categoryMapper::toDomain)
                    .toList();

            categoryCacheService.saveCategoriesToCache(categoriesFromRds, Duration.ofHours(1));
            result.addAll(categoriesFromRds);
        }
        return result;
    }

    @Override
    public CategoryRelation fetchCategoryRelation(long categoryId, boolean isParentRelation) {
        List<Category> relatedCategories = categoryQueryRepository.fetchRecursiveByIds(List.of(categoryId), !isParentRelation).stream()
                .map(categoryMapper::toDomain)
                .toList();

        return new CategoryRelation(categoryId, relatedCategories, isParentRelation);
    }


    @Override
    public List<CategoryRelation> fetchCategoryRelations(List<Long> categoryIds, boolean isParentRelation) {
        List<CategoryDto> recursiveResults = categoryQueryRepository.fetchRecursiveByIds(categoryIds, !isParentRelation);


        Map<Long, CategoryDto> categoryIdMap = recursiveResults.stream()
                .collect(Collectors.toMap(CategoryDto::getId, Function.identity(), (v1, v2) -> v2));

        return categoryIds.stream()
                .map(id -> {
                    List<Category> categories = Arrays.stream(categoryIdMap.get(id).getPath().split(","))
                            .map(Long::parseLong)
                            .map(categoryIdMap::get)
                            .filter(Objects::nonNull)
                            .map(categoryMapper::toDomain)
                            .toList();
                    return new CategoryRelation(id, categories, isParentRelation);
                })
                .toList();
    }

}
