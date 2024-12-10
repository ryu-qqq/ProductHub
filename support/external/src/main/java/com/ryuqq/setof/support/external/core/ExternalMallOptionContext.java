package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.OptionType;

import java.util.List;

public record ExternalMallOptionContext(
        long productGroupId,
        OptionType optionType,
        List<ExternalSyncProduct> products,
        List<ExternalSyncCategoryOption> externalCategoryOptions,
        ExternalSyncOptionResult gptOptionsResult,
        List<ExternalSyncStandardSize> standardSizes
) {}
