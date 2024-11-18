package com.ryuqq.setof.batch.core;

import com.ryuqq.setof.domain.core.product.Price;

public record ProductSearchBatchData(
        Long brandId, Long categoryId, Long sellerId, String productGroupName,
        String styleCode, String productCondition, String managementType,
        String optionType, Price price,
        boolean soldOutYn, boolean displayYn, String status, String keywords,
        String imageUrl
) {}
