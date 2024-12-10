package com.ryuqq.setof.domain.core.category;

public record MappingCategoryOptionCommand(
        long siteId,
        long externalCategoryId,
        long optionGroupId,
        Long optionId,
        String optionValue
) {}
