package com.ryuqq.setof.batch.core.product;

import java.util.List;
import java.util.Set;

public record ProductGroupBatchInsertData(
        Set<Long> sellerIds,
        List<Long> productGroupIds,
        ProductGroupBatchInsertEntities batchInsertEntities,
        List<ProductBatchInsertData> productBatchInserts
) {}

