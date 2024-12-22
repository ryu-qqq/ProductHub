package com.ryuqq.setof.domain.core.product;

public record ProductGroupInsertRequest(
        long productGroupId,
        ProductGroupCommandContext productGroupCommandContext
) {
}
