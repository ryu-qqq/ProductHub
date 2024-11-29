package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SyncStatus;

import java.math.BigDecimal;
import java.util.List;

public record ExternalMallUpdateResult(
        long productGroupId,
        long siteId,
        String externalProductId,
        String productName,
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        SyncStatus status,
        boolean soldOutYn,
        boolean displayYn,
        List<ExternalMallImageResult> externalMallImageResults
) {
}
