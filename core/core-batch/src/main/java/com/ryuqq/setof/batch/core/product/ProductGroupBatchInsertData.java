package com.ryuqq.setof.batch.core.product;

import java.util.List;

public record ProductGroupBatchInsertData(
        List<Long> productGroupIds,
        ProductGroupBatchInsertEntities batchInsertEntities,
        List<ProductBatchInsertData> productBatchInserts
) {
}
