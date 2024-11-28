package com.ryuqq.setof.api.core.controller.v1.category.service;

import com.ryuqq.setof.api.core.controller.v1.category.mapper.CategorySliceMapper;
import com.ryuqq.setof.api.core.controller.v1.category.request.CategoryGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.category.request.CategoryRelationGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.category.response.CategoryResponse;
import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.category.CategoryQueryService;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryQueryService categoryQueryService;
    private final CategorySliceMapper categorySliceMapper;

    public CategoryService(CategoryQueryService categoryQueryService, CategorySliceMapper categorySliceMapper) {
        this.categoryQueryService = categoryQueryService;
        this.categorySliceMapper = categorySliceMapper;
    }

    public Slice<CategoryResponse> fetchCategories(CategoryGetRequestDto categoryGetRequestDto){
        List<Category> categories = categoryQueryService.fetchCategoriesByFilter(categoryGetRequestDto.toCategoryFilter());
        long categoryCount = categoryQueryService.countByFilter(categoryGetRequestDto.toCategoryFilter());
        return categorySliceMapper.toSliceFromCategories(categories, categoryGetRequestDto.pageSize(), categoryCount);
    }

    public List<CategoryResponse> fetchCategoryRelation(CategoryRelationGetRequestDto requestDto){
        return categoryQueryService.fetchCategoryRelation(requestDto.categoryId(), requestDto.isParentRelation())
                .categories()
                .stream()
                .map(CategoryResponse::of)
                .toList();
    }

}
