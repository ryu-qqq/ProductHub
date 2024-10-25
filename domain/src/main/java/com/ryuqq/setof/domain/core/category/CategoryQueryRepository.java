package com.ryuqq.setof.domain.core.category;



import java.util.List;

public interface CategoryQueryRepository {

    boolean fetchCategoryExists(long categoryId);
    List<Category> fetchCategories(CategoryFilter categoryFilter);
    List<Category> fetchChildCategories(long categoryId);
    List<Category> fetchParentCategories(long categoryId);
    long fetchCategoryCount(CategoryFilter categoryFilter);

}
