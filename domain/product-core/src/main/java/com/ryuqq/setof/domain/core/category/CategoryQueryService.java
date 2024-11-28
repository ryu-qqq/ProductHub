package com.ryuqq.setof.domain.core.category;

import java.util.List;

public interface CategoryQueryService {

    boolean existById(long categoryId);
    List<Category> fetchCategoriesByFilter(CategoryFilter categoryFilter);
    List<Category> fetchCategoriesByIds(List<Long> categoryIds);

    long countByFilter(CategoryFilter categoryFilter);
    CategoryRelation fetchCategoryRelation(long categoryId, boolean isParentRelation);
    List<CategoryRelation> fetchCategoryRelations(List<Long> categoryIds, boolean isParentRelation);
}
