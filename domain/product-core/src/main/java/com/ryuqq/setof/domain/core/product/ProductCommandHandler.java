package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductCommandHandler implements ProductGroupUpdateHandler<ProductCommand> {

    private final ProductCommandService productCommandService;

    public ProductCommandHandler(ProductCommandService productCommandService) {
        this.productCommandService = productCommandService;
    }


    @Override
    public void handle(long productGroupId, ProductCommand command) {
        productCommandService.update(productGroupId, List.of(command));
    }

    @Override
    public Class<ProductCommand> getType() {
        return ProductCommand.class;
    }

}
