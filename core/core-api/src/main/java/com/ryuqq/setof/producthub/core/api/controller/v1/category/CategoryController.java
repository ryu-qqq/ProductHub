package com.ryuqq.setof.producthub.core.api.controller.v1.category;

import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.category.CategoryDomainQueryService;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.producthub.core.api.controller.v1.category.request.CategoryGetRequestDto;
import com.ryuqq.setof.producthub.core.api.controller.support.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ryuqq.setof.producthub.core.api.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class CategoryController {

    private final CategoryDomainQueryService categoryDomainQueryService;

    public CategoryController(CategoryDomainQueryService categoryDomainQueryService) {
        this.categoryDomainQueryService = categoryDomainQueryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<Slice<Category>>> getCategories(@ModelAttribute CategoryGetRequestDto categoryFilter) {
        return ResponseEntity.ok(ApiResponse.success(categoryDomainQueryService.getCategories(categoryFilter.toCategoryFilter())));
    }

    @GetMapping("/categories/{categoryId}/children")
    public ResponseEntity<ApiResponse<List<Category>>> getChildCategories(@PathVariable("categoryId") long categoryId) {
        return ResponseEntity.ok(ApiResponse.success(categoryDomainQueryService.getChildCategories(categoryId)));
    }

    @GetMapping("/categories/{categoryId}/parents")
    public ResponseEntity<ApiResponse<List<Category>>> getParentCategories(@PathVariable("categoryId") long categoryId) {
        return ResponseEntity.ok(ApiResponse.success(categoryDomainQueryService.getParentCategories(categoryId)));
    }

}
