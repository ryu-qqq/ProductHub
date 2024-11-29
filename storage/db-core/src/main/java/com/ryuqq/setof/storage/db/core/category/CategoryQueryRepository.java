package com.ryuqq.setof.storage.db.core.category;



import com.ryuqq.setof.storage.db.core.category.dto.CategoryDto;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryStorageFilterDto;

import java.util.List;

public interface CategoryQueryRepository {

    boolean existById(long categoryId);
    List<CategoryDto> fetchByFilter(CategoryStorageFilterDto categoryFilter);
    List<CategoryDto> fetchRecursiveByIds(List<Long> categoryIds, boolean isParentRelation);
    long countByFilter(CategoryStorageFilterDto categoryFilter);

}
