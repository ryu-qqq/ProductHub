package com.ryuqq.setof.domain.core.product;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupCommandFacade {

    private final ProductGroupCommandService productGroupCommandService;
    private final ProductNoticeCommandService productNoticeCommandService;
    private final ProductDeliveryCommandService productDeliveryCommandService;
    private final ProductGroupImageCommandService productGroupImageCommandService;
    private final ProductDetailDescriptionCommandService productDetailDescriptionCommandService;
    private final ProductCommandFacade productCommandFacade;
    private final ProductGroupQueryService productGroupQueryService;

    public ProductGroupCommandFacade(ProductGroupCommandService productGroupCommandService, ProductNoticeCommandService productNoticeCommandService, ProductDeliveryCommandService productDeliveryCommandService, ProductGroupImageCommandService productGroupImageCommandService, ProductDetailDescriptionCommandService productDetailDescriptionCommandService, ProductCommandFacade productCommandFacade, ProductGroupQueryService productGroupQueryService) {
        this.productGroupCommandService = productGroupCommandService;
        this.productNoticeCommandService = productNoticeCommandService;
        this.productDeliveryCommandService = productDeliveryCommandService;
        this.productGroupImageCommandService = productGroupImageCommandService;
        this.productDetailDescriptionCommandService = productDetailDescriptionCommandService;
        this.productCommandFacade = productCommandFacade;
        this.productGroupQueryService = productGroupQueryService;
    }

    @Transactional
    public long insert(ProductGroupCommandContext context) {
        long productGroupId = productGroupCommandService.insert(context.productGroupCommand());


        productNoticeCommandService.insert(productGroupId, context.productNoticeCommand());
        productDeliveryCommandService.insert(productGroupId, context.productDeliveryCommand());
        productGroupImageCommandService.inserts(productGroupId, context.productGroupImageCommands());
        productDetailDescriptionCommandService.insert(productGroupId, context.productDetailDescriptionCommand());
        productCommandFacade.insert(productGroupId, context.productCommands());

        return productGroupId;
    }

    @Transactional
    public long update(long productGroupId, ProductGroupCommandContext context) {
        ProductGroupContext productGroupContext = productGroupQueryService.findProductGroupContext(productGroupId);

        productGroupCommandService.update(productGroupContext.getProductGroup(), context.productGroupCommand());
        productNoticeCommandService.update(productGroupContext.getNotice(), context.productNoticeCommand());
        productDeliveryCommandService.update(productGroupContext.getDelivery(), context.productDeliveryCommand());
        productDetailDescriptionCommandService.update(productGroupId, context.productDetailDescriptionCommand());
        productGroupImageCommandService.updates(productGroupId, context.productGroupImageCommands());

        productCommandFacade.update(productGroupId, productGroupContext.getProducts(), context.productCommands());

        return productGroupId;
    }


}
