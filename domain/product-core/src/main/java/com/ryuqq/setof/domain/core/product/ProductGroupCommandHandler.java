package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

@Component
public class ProductGroupCommandHandler implements ProductGroupUpdateHandler<ProductGroupCommand> {

    private final ProductGroupCommandService productGroupCommandService;


    public ProductGroupCommandHandler(ProductGroupCommandService productGroupCommandService) {
        this.productGroupCommandService = productGroupCommandService;
    }

    @Override
    public void handle(long productGroupId, ProductGroupCommand productGroupCommand) {
        productGroupCommandService.update(productGroupId, productGroupCommand);
    }

    @Override
    public Class<ProductGroupCommand> getType() {
        return ProductGroupCommand.class;
    }


}
