package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupImageCommandHandler implements ProductGroupUpdateHandler<ProductGroupImageCommand> {

    private final ProductGroupImageCommandService productGroupImageCommandService;

    public ProductGroupImageCommandHandler(ProductGroupImageCommandService productGroupImageCommandService) {
        this.productGroupImageCommandService = productGroupImageCommandService;
    }

    @Override
    public void handle(long productGroupId, ProductGroupImageCommand command) {
        productGroupImageCommandService.updateAndInserts(productGroupId, List.of(command));
    }

    @Override
    public Class<ProductGroupImageCommand> getType() {
        return ProductGroupImageCommand.class;
    }

}
