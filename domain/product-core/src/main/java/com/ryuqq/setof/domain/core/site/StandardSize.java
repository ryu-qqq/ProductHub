package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.enums.core.SizeOrigin;

public record StandardSize(
        Long categoryId,
        Long regionId,
        SizeOrigin name,
        String sizeValue
) {
}
