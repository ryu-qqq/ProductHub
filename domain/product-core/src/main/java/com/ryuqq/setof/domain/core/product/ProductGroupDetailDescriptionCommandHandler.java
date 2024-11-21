package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

@Component
public class ProductGroupDetailDescriptionCommandHandler implements ProductGroupUpdateHandler<ProductDetailDescriptionCommand> {

    private final ProductDetailDescriptionCommandService productDetailDescriptionCommandService;

    public ProductGroupDetailDescriptionCommandHandler(ProductDetailDescriptionCommandService productDetailDescriptionCommandService) {
        this.productDetailDescriptionCommandService = productDetailDescriptionCommandService;
    }

    @Override
    public void handle(long productGroupId, ProductDetailDescriptionCommand command) {
        productDetailDescriptionCommandService.update(productGroupId, command);
    }

    @Override
    public Class<ProductDetailDescriptionCommand> getType() {
        return ProductDetailDescriptionCommand.class;
    }


}
