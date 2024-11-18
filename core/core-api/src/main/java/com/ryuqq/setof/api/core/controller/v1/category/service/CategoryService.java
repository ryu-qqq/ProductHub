package com.ryuqq.setof.api.core.controller.v1.category.service;

import com.ryuqq.setof.api.core.controller.v1.category.mapper.CategorySliceMapper;
import com.ryuqq.setof.api.core.controller.v1.category.request.CategoryGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.category.response.CategoryResponse;
import com.ryuqq.setof.domain.core.category.CategoryQueryService;
import com.ryuqq.setof.domain.core.category.CategoryRecord;
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


    public Slice<CategoryResponse> getCategories(CategoryGetRequestDto categoryGetRequestDto){
        List<CategoryRecord> categories = categoryQueryService.findCategories(categoryGetRequestDto.toCategoryFilter());
        long categoryCount = categoryQueryService.findCategoryCount(categoryGetRequestDto.toCategoryFilter());
        return categorySliceMapper.toSlice(toCategoryResponses(categories), categoryGetRequestDto.pageSize(), categoryCount);
    }


    public List<CategoryResponse> getChildCategories(long categoryId){
        List<CategoryRecord> categories = categoryQueryService.findChildCategories(categoryId);
        return toCategoryResponses(categories);
    }

    public List<CategoryResponse> getParentCategories(long categoryId){
        List<CategoryRecord> categories = categoryQueryService.findParentCategories(categoryId);
        return toCategoryResponses(categories);
    }

    private List<CategoryResponse> toCategoryResponses(List<CategoryRecord> CategoryRecords){
        return CategoryRecords.stream().map(CategoryResponse::of)
                .toList();
    }


}
