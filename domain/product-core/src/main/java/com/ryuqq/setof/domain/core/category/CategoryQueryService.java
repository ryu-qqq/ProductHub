package com.ryuqq.setof.domain.core.category;

import java.util.List;

public interface CategoryQueryService {

    boolean categoryExist(long categoryId);
    List<CategoryRecord> findCategories(CategoryFilter categoryFilter);
    List<CategoryRecord> findCategories(List<Long> categoryIds);
    List<CategoryRecord> findChildCategories(long categoryId);
    List<CategoryRecord> findParentCategories(long categoryId);
    long findCategoryCount(CategoryFilter categoryFilter);
}
