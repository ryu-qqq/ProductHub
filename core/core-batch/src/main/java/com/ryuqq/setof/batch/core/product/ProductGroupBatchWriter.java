package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.ProductCommandService;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryJdbcRepository;
import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionJdbcRepository;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupJdbcRepository;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageJdbcRepository;
import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticeJdbcRepository;
import com.ryuqq.setof.storage.db.core.site.ExternalProductJdbcRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductGroupBatchWriter implements ItemWriter<ProductGroupBatchInsertData> {

    private final ProductGroupJdbcRepository productGroupJdbcRepository;
    private final ProductDeliveryJdbcRepository deliveryRepository;
    private final ProductDetailDescriptionJdbcRepository detailDescriptionRepository;
    private final ProductGroupImageJdbcRepository imageRepository;
    private final ProductNoticeJdbcRepository noticeRepository;
    private final ProductCommandService productCommandService;
    private final ExternalProductJdbcRepository externalProductJdbcRepository;

    public ProductGroupBatchWriter(ProductGroupJdbcRepository productGroupJdbcRepository, ProductDeliveryJdbcRepository deliveryRepository, ProductDetailDescriptionJdbcRepository detailDescriptionRepository, ProductGroupImageJdbcRepository imageRepository, ProductNoticeJdbcRepository noticeRepository, ProductCommandService productCommandService, ExternalProductJdbcRepository externalProductJdbcRepository) {
        this.productGroupJdbcRepository = productGroupJdbcRepository;
        this.deliveryRepository = deliveryRepository;
        this.detailDescriptionRepository = detailDescriptionRepository;
        this.imageRepository = imageRepository;
        this.noticeRepository = noticeRepository;
        this.productCommandService = productCommandService;
        this.externalProductJdbcRepository = externalProductJdbcRepository;
    }


    @Override
    public void write(Chunk<? extends ProductGroupBatchInsertData> items) {
        List<Long> productGroupIds = new ArrayList<>();
        for (ProductGroupBatchInsertData data : items) {
            productGroupIds.addAll(data.productGroupIds());
            ProductGroupBatchInsertEntities batchInsertEntities = data.batchInsertEntities();
            deliveryRepository.batchInsertProductDeliveries(batchInsertEntities.getDeliveries());
            noticeRepository.batchInsertProductNotices(batchInsertEntities.getNotices());
            imageRepository.batchInsertProductGroupImages(batchInsertEntities.getImages());
            detailDescriptionRepository.batchInsertProductDetailDescriptions(batchInsertEntities.getDetailDescriptions());

            data.productBatchInserts().forEach(
                    productBatchInsertData ->
                        productCommandService.insert(productBatchInsertData.productGroupId(), productBatchInsertData.productCommands())
            );

            externalProductJdbcRepository.batchInsertExternalProducts(data.batchInsertEntities().getExternalProductEntities());
        }

        productGroupJdbcRepository.updatesProductStatus(productGroupIds, ProductStatus.PROCESSING);
    }

}
