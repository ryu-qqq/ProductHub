package com.ryuqq.setof.api.core.controller.v1.category.service;

import com.ryuqq.setof.api.core.controller.v1.product.service.ProductGroupFilterRequest;
import com.ryuqq.setof.domain.core.category.CategoryQueryService;
import com.ryuqq.setof.domain.core.category.CategoryRelation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryRelationProcessor {
    private final CategoryQueryService categoryQueryService;

    public CategoryRelationProcessor(CategoryQueryService categoryQueryService) {
        this.categoryQueryService = categoryQueryService;
    }

    public List<Long> processCategoryRelations(List<Long> categoryIds) {
        if (categoryIds != null && !categoryIds.isEmpty()) {
            return categoryQueryService.fetchCategoryRelations(categoryIds, false).stream()
                    .map(CategoryRelation::categoryId)
                    .distinct()
                    .toList();
        }
        return categoryIds; // 그대로 반환
    }

}