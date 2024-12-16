package com.ryuqq.setof.api.core.controller.v1.site.service;

import com.ryuqq.setof.api.core.controller.v1.category.service.CategoryRelationProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductGroupFilterProcessor {

    private final CategoryRelationProcessor categoryRelationProcessor;

    public ExternalProductGroupFilterProcessor(CategoryRelationProcessor categoryRelationProcessor) {
        this.categoryRelationProcessor = categoryRelationProcessor;
    }

    public ExternalProductGroupFilterRequest process(ExternalProductGroupFilterRequest request) {
        List<Long> processedCategoryIds = categoryRelationProcessor.processCategoryRelations(request.getProcessedCategoryIds());
        request.setProcessedCategoryIds(processedCategoryIds);
        return request;
    }
}
