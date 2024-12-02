package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;

import java.math.BigDecimal;
import java.util.List;

public record ExternalMallSyncResponse(
        SiteName siteName,
        long productGroupId,
        long setOfProductGroupId,
        String externalProductId,
        String productName,
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        List<ExternalMallImageRequestResult> imageRequestResults,
        int totalQuantity,
        boolean soldOutYn,
        boolean displayYn,
        String requestBody,
        ExternalMallRequestStatus externalMallRequestStatus
) {
}
