package com.ryuqq.setof.domain.core.category;

public record MappingCategoryCommand(
        String externalCategoryId,
        String externalExtraCategoryId,
        long categoryId,
        long siteId,
        String description
) {}
