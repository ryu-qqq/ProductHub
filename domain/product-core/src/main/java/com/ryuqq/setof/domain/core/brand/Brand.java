package com.ryuqq.setof.domain.core.brand;

public record Brand(
        long id,
        String brandName,
        String brandNameKr,
        String brandIconImageUrl,
        boolean displayYn
) {}
