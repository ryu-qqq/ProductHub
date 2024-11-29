package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.Price;
import com.ryuqq.setof.enums.core.SyncStatus;

public record ExternalProduct(
        long id,
        long siteId,
        long productGroupId,
        long policyId,
        String externalProductId,
        String productName,
        Price price,
        SyncStatus status,
        boolean soldOutYn,
        boolean displayYn,
        Long sellerId,
        long internalBrandId,
        long internalCategoryId,
        String externalBrandId,
        String externalCategoryId
) {}
