package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.ProductCommandService;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryPersistenceRepository;
import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionPersistenceRepository;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupConfigPersistenceRepository;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceRepository;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImagePersistenceRepository;
import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticePersistenceRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductGroupBatchWriter {

    private final ProductGroupPersistenceRepository productGroupPersistenceRepository;
    private final ProductGroupConfigPersistenceRepository productGroupConfigPersistenceRepository;
    private final ProductDeliveryPersistenceRepository productDeliveryPersistenceRepository;
    private final ProductDetailDescriptionPersistenceRepository productDetailDescriptionPersistenceRepository;
    private final ProductGroupImagePersistenceRepository productGroupImagePersistenceRepository;
    private final ProductNoticePersistenceRepository productNoticePersistenceRepository;
    private final ProductCommandService productCommandService;

    public ProductGroupBatchWriter(ProductGroupPersistenceRepository productGroupPersistenceRepository, ProductGroupConfigPersistenceRepository productGroupConfigPersistenceRepository, ProductDeliveryPersistenceRepository productDeliveryPersistenceRepository, ProductDetailDescriptionPersistenceRepository productDetailDescriptionPersistenceRepository, ProductGroupImagePersistenceRepository productGroupImagePersistenceRepository, ProductNoticePersistenceRepository productNoticePersistenceRepository, ProductCommandService productCommandService) {
        this.productGroupPersistenceRepository = productGroupPersistenceRepository;
        this.productGroupConfigPersistenceRepository = productGroupConfigPersistenceRepository;
        this.productDeliveryPersistenceRepository = productDeliveryPersistenceRepository;
        this.productDetailDescriptionPersistenceRepository = productDetailDescriptionPersistenceRepository;
        this.productGroupImagePersistenceRepository = productGroupImagePersistenceRepository;
        this.productNoticePersistenceRepository = productNoticePersistenceRepository;
        this.productCommandService = productCommandService;
    }


    public void write(Chunk<? extends ProductGroupBatchInsertData> items) {
        List<Long> productGroupIds = new ArrayList<>();
        for (ProductGroupBatchInsertData data : items) {
            productGroupIds.addAll(data.productGroupIds());
            ProductGroupBatchInsertEntities batchInsertEntities = data.batchInsertEntities();
            productDeliveryPersistenceRepository.saveAllProductDelivery(batchInsertEntities.getDeliveries());
            productNoticePersistenceRepository.insertAllProductNotice(batchInsertEntities.getNotices());
            productGroupImagePersistenceRepository.saveAllProductGroupImage(batchInsertEntities.getImages());
            productDetailDescriptionPersistenceRepository.saveAllProductDetailDescription(batchInsertEntities.getDetailDescriptions());
            productGroupConfigPersistenceRepository.saveAllProductGroupConfigEntities(batchInsertEntities.getProductGroupConfigEntities());

            data.productBatchInserts().forEach(
                    productBatchInsertData ->
                        productCommandService.insert(productBatchInsertData.productGroupId(), productBatchInsertData.productCommands())
            );
        }

        productGroupPersistenceRepository.updatesProductStatus(productGroupIds, ProductStatus.PROCESSING);
    }

}
