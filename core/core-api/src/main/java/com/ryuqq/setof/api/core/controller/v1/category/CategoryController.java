package com.ryuqq.setof.api.core.controller.v1.category;

import com.ryuqq.setof.api.core.controller.support.ApiResponse;
import com.ryuqq.setof.api.core.controller.v1.category.request.CategoryGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.category.response.CategoryResponse;
import com.ryuqq.setof.api.core.controller.v1.category.service.CategoryService;
import com.ryuqq.setof.domain.core.generic.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ryuqq.setof.api.core.controller.config.EndPointsConstants.BASE_END_POINT_V1;

@RequestMapping(BASE_END_POINT_V1)
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<Slice<CategoryResponse>>> getCategories(@ModelAttribute CategoryGetRequestDto categoryFilter) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getCategories(categoryFilter)));
    }

    @GetMapping("/categories/{categoryId}/children")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getChildCategories(@PathVariable("categoryId") long categoryId) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getChildCategories(categoryId)));
    }

    @GetMapping("/categories/{categoryId}/parents")
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getParentCategories(@PathVariable("categoryId") long categoryId) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getParentCategories(categoryId)));
    }

}
