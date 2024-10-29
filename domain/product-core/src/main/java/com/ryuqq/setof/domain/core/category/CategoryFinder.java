package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.storage.db.core.category.CategoryQueryRepository;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryFinder implements CategoryQueryService {

    private final CategoryQueryRepository categoryQueryRepository;

    public CategoryFinder(CategoryQueryRepository categoryQueryRepository) {
        this.categoryQueryRepository = categoryQueryRepository;
    }
    @Override
    public boolean categoryExist(long categoryId){
        return categoryQueryRepository.fetchCategoryExists(categoryId);
    }

    @Override
    public List<CategoryRecord> findCategories(CategoryFilter categoryFilter){
        List<CategoryDto> results = categoryQueryRepository.fetchCategories(categoryFilter.toStorageFilter());
        return results.stream()
                .map(CategoryRecord::toCategoryRecord)
                .toList();
    }

    @Override
    public List<CategoryRecord> findCategories(List<Long> categoryIds){
        CategoryFilter categoryFilter = new CategoryFilter(categoryIds, null, null, null, categoryIds.size());
        return findCategories(categoryFilter);
    }

    @Override
    public List<CategoryRecord> findChildCategories(long categoryId){
        List<CategoryDto> results = categoryQueryRepository.fetchChildCategories(categoryId);
        return results.stream()
                .map(CategoryRecord::toCategoryRecord)
                .toList();
    }

    @Override
    public List<CategoryRecord> findParentCategories(long categoryId){
        List<CategoryDto> results = categoryQueryRepository.fetchParentCategories(categoryId);
        return results.stream()
                .map(CategoryRecord::toCategoryRecord)
                .toList();
    }

    @Override
    public long findCategoryCount(CategoryFilter categoryFilter){
        return categoryQueryRepository.fetchCategoryCount(categoryFilter.toStorageFilter());
    }

}
