package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.ProductCommand;
import com.ryuqq.setof.domain.core.product.ProductGroupCommandContext;
import com.ryuqq.setof.domain.core.product.ProductGroupInsertRequest;
import com.ryuqq.setof.db.core.product.delivery.ProductDeliveryEntity;
import com.ryuqq.setof.db.core.product.description.ProductDetailDescriptionEntity;
import com.ryuqq.setof.db.core.product.group.ProductGroupConfigEntity;
import com.ryuqq.setof.db.core.product.image.ProductGroupImageEntity;
import com.ryuqq.setof.db.core.product.notice.ProductNoticeEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ProductGroupBatchProcessor implements ItemProcessor<List<ProductGroupInsertRequest>, ProductGroupBatchInsertData> {

    @Override
    public ProductGroupBatchInsertData process(List<ProductGroupInsertRequest> insertRequests) {
        ProductGroupBatchInsertEntities productGroupBatchInsertEntities = new ProductGroupBatchInsertEntities();
        List<ProductBatchInsertData> productBatchInserts = new ArrayList<>();
        Set<Long> sellerIds = new HashSet<>();
        List<Long> productGroupIds = new ArrayList<>();

        insertRequests.forEach(i -> {
            ProductGroupCommandContext document = i.productGroupCommandContext();

            sellerIds.add(document.productGroupCommand().sellerId());

            long productGroupId = i.productGroupId();
            productGroupIds.add(productGroupId);

            productGroupBatchInsertEntities.addDelivery(new ProductDeliveryEntity(
                    productGroupId,
                    document.productDeliveryCommand().deliveryArea(),
                    document.productDeliveryCommand().deliveryFee(),
                    document.productDeliveryCommand().deliveryPeriodAverage(),
                    document.productDeliveryCommand().returnMethodDomestic(),
                    document.productDeliveryCommand().returnCourierDomestic(),
                    document.productDeliveryCommand().returnChargeDomestic(),
                    document.productDeliveryCommand().returnExchangeAreaDomestic()
            ));

            productGroupBatchInsertEntities.addNotice(new ProductNoticeEntity(
                    productGroupId,
                    document.productNoticeCommand().material(),
                    document.productNoticeCommand().color(),
                    document.productNoticeCommand().size(),
                    document.productNoticeCommand().maker(),
                    document.productNoticeCommand().origin(),
                    document.productNoticeCommand().washingMethod(),
                    document.productNoticeCommand().yearMonth(),
                    document.productNoticeCommand().assuranceStandard(),
                    document.productNoticeCommand().asPhone()
            ));


            productGroupBatchInsertEntities.addDetailDescription(new ProductDetailDescriptionEntity(
                    productGroupId,
                    document.productDetailDescriptionCommand().detailDescription()
            ));

            document.productGroupImageCommands().forEach(image ->
                    productGroupBatchInsertEntities.addImages(new ProductGroupImageEntity(
                            productGroupId,
                            image.productImageType(),
                            image.imageUrl(),
                            image.imageUrl()
                    ))
            );

            productGroupBatchInsertEntities.addProductGroupConfig(new ProductGroupConfigEntity(productGroupId, true));

            List<ProductCommand> allProductCommands = document.productCommands().stream()
                    .map(product ->
                            new ProductCommand(
                                    null,
                                    product.soldOutYn(),
                                    product.displayYn(),
                                    product.quantity(),
                                    product.additionalPrice(),
                                    product.option(),
                                    product.options(),
                                    false
                            )
                    ).toList();

            productBatchInserts.add(new ProductBatchInsertData(productGroupId, allProductCommands));
        });

        return new ProductGroupBatchInsertData(sellerIds, productGroupIds, productGroupBatchInsertEntities, productBatchInserts);
    }

}
