package com.ryuqq.setof.support.external.core;

import java.util.List;

public record ProcessingOptionResult(
        long productGroupId,
        List<String> originalOptions,
        NormalizedOptions normalizedOptions
) {

    public record NormalizedOptions(
            List<String> sizes,
            String unit
    ) {}
}
