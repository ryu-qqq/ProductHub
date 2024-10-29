package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryDomainQueryService {

    private final CategoryFinder categoryFinder;
    private final CategorySliceMapper categorySliceMapper;

    public CategoryDomainQueryService(CategoryFinder categoryFinder, CategorySliceMapper categorySliceMapper) {
        this.categoryFinder = categoryFinder;
        this.categorySliceMapper = categorySliceMapper;
    }

    public Slice<Category> getCategories(CategoryFilter categoryFilter){
        List<Category> categories = categoryFinder.findCategories(categoryFilter);
        long categoryCount = categoryFinder.findCategoryCount(categoryFilter);
        return categorySliceMapper.toSlice(categories, categoryFilter.pageSize(), categoryCount);
    }

    public List<Category> getCategories(List<Long> categoryIds){
        CategoryFilter categoryFilter = new CategoryFilter(categoryIds, null, null, null, categoryIds.size());
        return categoryFinder.findCategories(categoryFilter);
    }


    public List<Category> getChildCategories(long categoryId){
        return categoryFinder.findChildCategories(categoryId);
    }

    public List<Category> getParentCategories(long categoryId){
        return categoryFinder.findParentCategories(categoryId);
    }

}
