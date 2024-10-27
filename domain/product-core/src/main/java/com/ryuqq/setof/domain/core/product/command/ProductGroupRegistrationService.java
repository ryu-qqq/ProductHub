package com.ryuqq.setof.domain.core.product.command;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupRegistrationService {

    private final ProductGroupCommandService productGroupCommandService;
    private final ProductNoticeCommandService productNoticeCommandService;
    private final ProductDeliveryCommandService productDeliveryCommandService;

    private final ProductGroupImageCommandService productGroupImageCommandService;
    private final ProductDetailDescriptionCommandService productDetailDescriptionCommandService;

    private final ProductCommandService productCommandService;

    public ProductGroupRegistrationService(ProductGroupCommandService productGroupCommandService, ProductNoticeCommandService productNoticeCommandService, ProductDeliveryCommandService productDeliveryCommandService, ProductGroupImageCommandService productGroupImageCommandService, ProductDetailDescriptionCommandService productDetailDescriptionCommandService, ProductCommandService productCommandService) {
        this.productGroupCommandService = productGroupCommandService;
        this.productNoticeCommandService = productNoticeCommandService;
        this.productDeliveryCommandService = productDeliveryCommandService;
        this.productGroupImageCommandService = productGroupImageCommandService;
        this.productDetailDescriptionCommandService = productDetailDescriptionCommandService;
        this.productCommandService = productCommandService;
    }

    public ProductGroupCommandResponse insert(ProductGroupCommandContext context) {
        long productGroupId = productGroupCommandService.insert(context.productGroupCommand());
        productNoticeCommandService.insert(productGroupId, context.productNoticeCommand());
        productDeliveryCommandService.insert(productGroupId, context.productDeliveryCommand());

        productGroupImageCommandService.inserts(productGroupId, context.productGroupImageCommands());
        productDetailDescriptionCommandService.insert(productGroupId, context.productDetailDescriptionCommand());

        List<Long> productIds = productCommandService.insert(productGroupId, context.productCommands());

        return new ProductGroupCommandResponse(productGroupId, productIds);
    }


}
