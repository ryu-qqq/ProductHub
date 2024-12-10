package com.ryuqq.setof.support.external.core;

public record ExternalSyncCategoryOptionCommand(
        long externalCategoryId,
        long optionGroupId,
        Long optionId,
        String optionValue
) {
}
