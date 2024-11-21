package com.ryuqq.setof.domain.core.product;

public abstract class AbstractProductGroupUpdateHandler implements ProductGroupUpdateHandler {

    private final ProductGroupQueryService productGroupQueryService;

    protected AbstractProductGroupUpdateHandler(ProductGroupQueryService productGroupQueryService) {
        this.productGroupQueryService = productGroupQueryService;
    }


}
