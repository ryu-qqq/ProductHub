package com.ryuqq.setof.support.external.core;

public record ExternalSyncCategoryOption(
        long siteId,
        String externalCategoryId,
        long optionGroupId,
        Long optionId,
        String optionValue
) {
}
