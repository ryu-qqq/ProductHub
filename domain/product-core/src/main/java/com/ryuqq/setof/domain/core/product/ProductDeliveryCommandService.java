package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryPersistenceRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDeliveryCommandService {

    private final ProductDeliveryPersistenceRepository productNoticePersistenceService;

    public ProductDeliveryCommandService(ProductDeliveryPersistenceRepository productNoticePersistenceService) {
        this.productNoticePersistenceService = productNoticePersistenceService;
    }

    public void insert(long productGroupId, ProductDeliveryCommand productDeliveryCommand) {
        productNoticePersistenceService.saveProductDelivery(productDeliveryCommand.toEntity(productGroupId));
    }

    public void update(long productGroupId, ProductDeliveryCommand productDeliveryCommand) {
        productNoticePersistenceService.updateProductDelivery(productDeliveryCommand.toEntity(productGroupId));
    }

}
