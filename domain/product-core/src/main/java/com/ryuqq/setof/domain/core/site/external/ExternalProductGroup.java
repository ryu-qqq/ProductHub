package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.Price;
import com.ryuqq.setof.enums.core.SyncStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ExternalProductGroup(
        long id,
        long siteId,
        String siteName,
        long productGroupId,
        long policyId,
        String externalProductGroupId,
        String productName,
        String originName,
        Price price,
        SyncStatus status,
        boolean soldOutYn,
        boolean displayYn,
        Long sellerId,
        long internalBrandId,
        long internalCategoryId,
        List<Long> categoryPath,
        String externalBrandId,
        String externalCategoryId,
        String externalExtraCategoryId,
        LocalDateTime insertTime,
        LocalDateTime updateTime,
        List<ExternalProductImage> externalProductImages
) {}
