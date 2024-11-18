package com.ryuqq.setof.batch.core;

import com.ryuqq.setof.domain.core.product.ProductCommandFacade;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryJdbcRepository;
import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionJdbcRepository;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupJdbcRepository;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageJdbcRepository;
import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticeJdbcRepository;
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
    private final ProductCommandFacade productCommandFacade;

    public ProductGroupBatchWriter(ProductGroupJdbcRepository productGroupJdbcRepository, ProductDeliveryJdbcRepository deliveryRepository, ProductDetailDescriptionJdbcRepository detailDescriptionRepository, ProductGroupImageJdbcRepository imageRepository, ProductNoticeJdbcRepository noticeRepository, ProductCommandFacade productCommandFacade) {
        this.productGroupJdbcRepository = productGroupJdbcRepository;
        this.deliveryRepository = deliveryRepository;
        this.detailDescriptionRepository = detailDescriptionRepository;
        this.imageRepository = imageRepository;
        this.noticeRepository = noticeRepository;
        this.productCommandFacade = productCommandFacade;
    }


    @Override
    public void write(Chunk<? extends ProductGroupBatchInsertData> items) throws Exception {
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
                        productCommandFacade.insert(productBatchInsertData.productGroupId(), productBatchInsertData.productCommands())
            );
        }
        productGroupJdbcRepository.updatesProductStatus(productGroupIds, ProductStatus.APPROVED);
    }
}
