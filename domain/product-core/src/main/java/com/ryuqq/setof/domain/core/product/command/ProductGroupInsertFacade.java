package com.ryuqq.setof.domain.core.product.command;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupInsertFacade {

    private final ProductGroupCommandService productGroupCommandService;
    private final ProductNoticeCommandService productNoticeCommandService;
    private final ProductDeliveryCommandService productDeliveryCommandService;
    private final ProductGroupImageCommandService productGroupImageCommandService;
    private final ProductDetailDescriptionCommandService productDetailDescriptionCommandService;
    private final ProductInsertFacade productInsertFacade;

    public ProductGroupInsertFacade(ProductGroupCommandService productGroupCommandService, ProductNoticeCommandService productNoticeCommandService, ProductDeliveryCommandService productDeliveryCommandService, ProductGroupImageCommandService productGroupImageCommandService, ProductDetailDescriptionCommandService productDetailDescriptionCommandService, ProductInsertFacade productInsertFacade) {
        this.productGroupCommandService = productGroupCommandService;
        this.productNoticeCommandService = productNoticeCommandService;
        this.productDeliveryCommandService = productDeliveryCommandService;
        this.productGroupImageCommandService = productGroupImageCommandService;
        this.productDetailDescriptionCommandService = productDetailDescriptionCommandService;
        this.productInsertFacade = productInsertFacade;
    }

    @Transactional
    public long insert(ProductGroupCommandContext context) {
        long productGroupId = productGroupCommandService.insert(context.productGroupCommand());
        productNoticeCommandService.insert(productGroupId, context.productNoticeCommand());
        productDeliveryCommandService.insert(productGroupId, context.productDeliveryCommand());

        productGroupImageCommandService.inserts(productGroupId, context.productGroupImageCommands());
        productDetailDescriptionCommandService.insert(productGroupId, context.productDetailDescriptionCommand());

        productInsertFacade.insert(productGroupId, context.productCommands());

        return productGroupId;
    }


}
