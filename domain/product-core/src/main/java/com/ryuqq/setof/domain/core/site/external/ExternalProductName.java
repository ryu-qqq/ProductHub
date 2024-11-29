package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.Origin;

public record ExternalProductName(
        Origin countryCode,
        String productName
) {
}
