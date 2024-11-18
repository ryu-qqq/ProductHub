package com.ryuqq.setof.batch.core;

import com.ryuqq.setof.domain.core.product.OptionCommand;
import com.ryuqq.setof.domain.core.product.ProductCommand;
import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryEntity;
import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionEntity;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;
import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticeEntity;
import com.ryuqq.setof.storage.db.index.product.OptionDocument;
import com.ryuqq.setof.storage.db.index.product.ProductGroupCommandContextDocument;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductGroupBatchProcessor implements ItemProcessor<List<ProductGroupCommandContextDocument>, ProductGroupBatchInsertData> {

    @Override
    public ProductGroupBatchInsertData process(List<ProductGroupCommandContextDocument> documents) throws Exception {
        ProductGroupBatchInsertEntities batchInsertEntities = new ProductGroupBatchInsertEntities();
        List<ProductBatchInsertData> productBatchInserts = new ArrayList<>();
        List<Long> productGroupIds = new ArrayList<>();

        documents.forEach(document -> {
            Long productGroupId = Long.parseLong(document.getProductGroupId());
            productGroupIds.add(productGroupId);

            batchInsertEntities.addDelivery(new ProductDeliveryEntity(
                    productGroupId,
                    document.getProductDelivery().deliveryArea(),
                    document.getProductDelivery().deliveryFee(),
                    document.getProductDelivery().deliveryPeriodAverage(),
                    document.getProductDelivery().returnMethodDomestic(),
                    document.getProductDelivery().returnCourierDomestic(),
                    document.getProductDelivery().returnChargeDomestic(),
                    document.getProductDelivery().returnExchangeAreaDomestic()
            ));


            batchInsertEntities.addNotice(new ProductNoticeEntity(
                    productGroupId,
                    document.getProductNotice().material(),
                    document.getProductNotice().color(),
                    document.getProductNotice().size(),
                    document.getProductNotice().maker(),
                    document.getProductNotice().origin(),
                    document.getProductNotice().washingMethod(),
                    document.getProductNotice().yearMonth(),
                    document.getProductNotice().assuranceStandard(),
                    document.getProductNotice().asPhone()
            ));


            batchInsertEntities.addDetailDescription(new ProductDetailDescriptionEntity(
                    productGroupId,
                    document.getDetailDescription().detailDescription()
            ));

            document.getProductImages().forEach(image ->
                    batchInsertEntities.addImages(new ProductGroupImageEntity(
                            productGroupId,
                            image.productImageType(),
                            image.imageUrl(),
                            image.originUrl()
                    ))
            );

            List<ProductCommand> allProductCommands = document.getProducts().stream()
                    .map(product ->
                            new ProductCommand(
                                    product.soldOutYn(),
                                    product.displayYn(),
                                    product.quantity(),
                                    product.additionalPrice(),
                                    toOptionCommands(product.options())
                            )
                    ).toList();

            productBatchInserts.add(new ProductBatchInsertData(productGroupId, allProductCommands));
        });

        return new ProductGroupBatchInsertData(productGroupIds, batchInsertEntities, productBatchInserts);
    }

    private List<OptionCommand> toOptionCommands(List<OptionDocument> optionDocuments){
        return optionDocuments.stream().map(o -> new OptionCommand(o.optionName(), o.optionValue())).toList();
    }

}
