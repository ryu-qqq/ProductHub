package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;

import java.util.List;

public record ExternalMallProductContext(
        long siteId,
        SiteName siteName,
        ExternalMallBrand brand,
        ExternalMallCategory category,
        ExternalMallProductGroup productGroup,
        List<ExternalMallProduct> products,
        List<ExternalMallCategoryOption> externalCategoryOptions,
        ProcessingOptionResult gptOptionsResult
) {
}
