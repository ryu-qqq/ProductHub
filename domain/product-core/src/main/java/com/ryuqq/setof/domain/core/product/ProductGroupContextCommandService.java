package com.ryuqq.setof.domain.core.product;

public interface ProductGroupContextCommandService {

    long insert(ProductGroupCommandContext context);
    long update(long productGroupId, ProductGroupCommandContext context);

}
