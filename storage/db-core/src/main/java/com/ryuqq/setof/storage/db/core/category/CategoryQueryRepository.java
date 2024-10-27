package com.ryuqq.setof.storage.db.core.category;



import com.ryuqq.setof.storage.db.core.category.dto.CategoryDto;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryStorageFilterDto;

import java.util.List;

public interface CategoryQueryRepository {

    boolean fetchCategoryExists(long categoryId);
    List<CategoryDto> fetchCategories(CategoryStorageFilterDto categoryFilter);
    List<CategoryDto> fetchChildCategories(long categoryId);
    List<CategoryDto> fetchParentCategories(long categoryId);
    long fetchCategoryCount(CategoryStorageFilterDto categoryFilter);

}
