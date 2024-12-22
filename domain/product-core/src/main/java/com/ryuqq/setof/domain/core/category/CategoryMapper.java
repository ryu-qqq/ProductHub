package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.cache.core.category.CategoryCacheDto;
import com.ryuqq.setof.db.core.category.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toDomain(CategoryDto CategoryDto) {
        return new Category(
                CategoryDto.getId(),
                CategoryDto.getCategoryName(),
                CategoryDto.getDepth(),
                CategoryDto.getParentCategoryId(),
                CategoryDto.isDisplayYn(),
                CategoryDto.getTargetGroup(),
                CategoryDto.getCategoryType(),
                CategoryDto.getPath()
        );
    }

    public Category toDomain(CategoryCacheDto cacheDto) {
        return new Category(
                cacheDto.id(),
                cacheDto.categoryName(),
                cacheDto.depth(),
                cacheDto.parentCategoryId(),
                cacheDto.displayYn(),
                cacheDto.targetGroup(),
                cacheDto.categoryType(),
                cacheDto.path()
        );
    }

    public CategoryCacheDto toCacheDto(Category category) {
        return new CategoryCacheDto(
                category.id(),
                category.categoryName(),
                category.depth(),
                category.parentCategoryId(),
                category.displayYn(),
                category.targetGroup(),
                category.categoryType(),
                category.path()
        );
    }

}
