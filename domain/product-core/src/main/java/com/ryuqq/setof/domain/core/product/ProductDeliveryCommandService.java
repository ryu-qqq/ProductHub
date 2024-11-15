package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryPersistenceService;
import org.springframework.stereotype.Service;

@Service
public class ProductDeliveryCommandService {

    private final ProductDeliveryPersistenceService productNoticePersistenceService;

    public ProductDeliveryCommandService(ProductDeliveryPersistenceService productNoticePersistenceService) {
        this.productNoticePersistenceService = productNoticePersistenceService;
    }

    public void insert(long productGroupId, ProductDeliveryCommand productDeliveryCommand) {
        productNoticePersistenceService.insert(productDeliveryCommand.toEntity(productGroupId));
    }

    public void update(ProductDelivery productDelivery, ProductDeliveryCommand productDeliveryCommand) {
        ProductDeliveryUpdater updater = new ProductDeliveryUpdater(productDelivery);

        if (updater.hasUpdates(productDeliveryCommand)) {
            productNoticePersistenceService.update(productDeliveryCommand.toEntity(productDelivery.getProductGroupId()));
        }
    }

}