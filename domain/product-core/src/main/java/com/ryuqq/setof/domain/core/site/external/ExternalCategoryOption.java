package com.ryuqq.setof.domain.core.site.external;

public record ExternalCategoryOption(
        long siteId,
        long externalCategoryId,
        long optionId,
        String optionValue
) {
}
