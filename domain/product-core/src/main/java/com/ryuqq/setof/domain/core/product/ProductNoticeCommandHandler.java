package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

@Component
public class ProductNoticeCommandHandler implements ProductGroupUpdateHandler<ProductNoticeCommand>{

    private final ProductNoticeCommandService productNoticeCommandService;

    public ProductNoticeCommandHandler(ProductNoticeCommandService productNoticeCommandService) {
        this.productNoticeCommandService = productNoticeCommandService;
    }

    @Override
    public void handle(long productGroupId, ProductNoticeCommand productNoticeCommand) {
        productNoticeCommandService.update(productGroupId, productNoticeCommand);
    }

    @Override
    public Class<ProductNoticeCommand> getType() {
        return ProductNoticeCommand.class;
    }


}
