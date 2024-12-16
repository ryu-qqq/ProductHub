package com.ryuqq.setof.api.core.controller.v1.product.service;

import com.ryuqq.setof.api.core.controller.v1.category.service.CategoryRelationProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupFilterProcessor {

    private final CategoryRelationProcessor categoryRelationProcessor;

    public ProductGroupFilterProcessor(CategoryRelationProcessor categoryRelationProcessor) {
        this.categoryRelationProcessor = categoryRelationProcessor;
    }

    public ProductGroupFilterRequest process(ProductGroupFilterRequest request) {
        List<Long> processedCategoryIds = categoryRelationProcessor.processCategoryRelations(request.getProcessedCategoryIds());
        request.setProcessedCategoryIds(processedCategoryIds);
        return request;
    }
}