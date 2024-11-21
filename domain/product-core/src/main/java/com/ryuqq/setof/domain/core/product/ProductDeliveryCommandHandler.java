package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

@Component
public class ProductDeliveryCommandHandler implements ProductGroupUpdateHandler<ProductDeliveryCommand> {

    private final ProductDeliveryCommandService productDeliveryCommandService;

    public ProductDeliveryCommandHandler(ProductDeliveryCommandService productDeliveryCommandService) {
        this.productDeliveryCommandService = productDeliveryCommandService;
    }


    @Override
    public void handle(long productGroupId, ProductDeliveryCommand productDeliveryCommand) {
        productDeliveryCommandService.update(productGroupId, productDeliveryCommand);
    }

    @Override
    public Class<ProductDeliveryCommand> getType() {
        return ProductDeliveryCommand.class;
    }

}
