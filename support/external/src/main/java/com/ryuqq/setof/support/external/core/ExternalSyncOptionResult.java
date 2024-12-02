package com.ryuqq.setof.support.external.core;

import java.util.List;

public record ExternalSyncOptionResult(
        long productGroupId,
        List<String> originalOptions,
        NormalizedOptions normalizedOptions
) {

    public record NormalizedOptions(
            List<String> sizes,
            String unit
    ) {}
}
