package com.ryuqq.setof.domain.core.site.external;

public record ExternalCategoryOption(
        long siteId,
        String externalCategoryId,
        long optionGroupId,
        Long optionId,
        String optionValue
) {
}
