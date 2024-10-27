package com.ryuqq.setof.producthub.core.api.controller.v1.category;

import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.category.CategoryService;
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

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<Slice<Category>>> getCategories(@ModelAttribute CategoryGetRequestDto categoryFilter) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getCategories(categoryFilter.toCategoryFilter())));
    }

    @GetMapping("/categories/{categoryId}/children")
    public ResponseEntity<ApiResponse<List<Category>>> getChildCategories(@PathVariable("categoryId") long categoryId) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getChildCategories(categoryId)));
    }

    @GetMapping("/categories/{categoryId}/parents")
    public ResponseEntity<ApiResponse<List<Category>>> getParentCategories(@PathVariable("categoryId") long categoryId) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getParentCategories(categoryId)));
    }

}
