package com.ryuqq.setof.batch.core;

import com.ryuqq.setof.domain.core.product.ProductCommand;

import java.util.List;

public record ProductBatchInsertData(
        long productGroupId,
        List<ProductCommand> productCommands) {
}
