package com.ryuqq.setof.domain.core.product;

import org.springframework.stereotype.Component;

@Component
public class ProductGroupContextUpdater {

    private final ProductGroupChecker productGroupChecker;
    private final ProductDeliveryChecker productDeliveryChecker;
    private final ProductNoticeChecker productNoticeChecker;
    private final ProductGroupImageChecker productGroupImageChecker;
    private final ProductGroupDetailDescriptionChecker productGroupDetailDescriptionChecker;
    private final ProductChecker productChecker;

    public ProductGroupContextUpdater(ProductGroupChecker productGroupChecker, ProductDeliveryChecker productDeliveryChecker, ProductNoticeChecker productNoticeChecker, ProductGroupImageChecker productGroupImageChecker, ProductGroupDetailDescriptionChecker productGroupDetailDescriptionChecker, ProductChecker productChecker) {

        this.productGroupChecker = productGroupChecker;
        this.productDeliveryChecker = productDeliveryChecker;
        this.productNoticeChecker = productNoticeChecker;
        this.productGroupImageChecker = productGroupImageChecker;
        this.productGroupDetailDescriptionChecker = productGroupDetailDescriptionChecker;
        this.productChecker = productChecker;
    }

    public UpdateDecision checkUpdates(ProductGroupContext existingContext, ProductGroupCommandContext newContext) {
        UpdateDecision decision = new UpdateDecision();

        UpdateDecision groupDecision = productGroupChecker.checkUpdates(existingContext.getProductGroup(), newContext.productGroupCommand());
        decision.merge(groupDecision);

        UpdateDecision deliveryDecision = productDeliveryChecker.checkUpdates(existingContext.getDelivery(), newContext.productDeliveryCommand());
        decision.merge(deliveryDecision);

        UpdateDecision noticeDecision = productNoticeChecker.checkUpdates(existingContext.getNotice(), newContext.productNoticeCommand());
        decision.merge(noticeDecision);

        UpdateDecision imagesDecision = productGroupImageChecker.checkUpdates(existingContext.getImages(), newContext.productGroupImageCommands());
        decision.merge(imagesDecision);

        UpdateDecision descriptionDecision = productGroupDetailDescriptionChecker.checkUpdates(existingContext.getDetailDescription(), newContext.productDetailDescriptionCommand());
        decision.merge(descriptionDecision);

        UpdateDecision productDecision = productChecker.checkUpdates(existingContext.getProducts(), newContext.productCommands());
        decision.merge(productDecision);

        return decision;
    }

}
