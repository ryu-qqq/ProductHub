package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.OptionCommand;
import com.ryuqq.setof.domain.core.product.ProductCommand;
import com.ryuqq.setof.storage.db.core.product.delivery.ProductDeliveryEntity;
import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionEntity;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupConfigEntity;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageEntity;
import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticeEntity;
import com.ryuqq.setof.storage.db.core.site.ExternalProductEntity;
import com.ryuqq.setof.storage.db.index.product.OptionDocument;
import com.ryuqq.setof.storage.db.index.product.ProductGroupCommandContextDocument;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ProductGroupBatchProcessor implements ItemProcessor<List<ProductGroupProcessingData>, ProductGroupBatchInsertData> {

    @Override
    public ProductGroupBatchInsertData process(List<ProductGroupProcessingData> documents) {
        ProductGroupBatchInsertEntities productGroupBatchInsertEntities = new ProductGroupBatchInsertEntities();
        List<ProductBatchInsertData> productBatchInserts = new ArrayList<>();
        Set<Long> sellerIds = new HashSet<>();
        List<Long> productGroupIds = new ArrayList<>();

        documents.forEach(d -> {
            ProductGroupCommandContextDocument document = d.productGroupCommandContextDocument();

            sellerIds.add(document.getProductGroupCommandDocument().sellerId());

            long productGroupId = Long.parseLong(document.getProductGroupId());
            productGroupIds.add(productGroupId);

            productGroupBatchInsertEntities.addDelivery(new ProductDeliveryEntity(
                    productGroupId,
                    document.getProductDelivery().deliveryArea(),
                    document.getProductDelivery().deliveryFee(),
                    document.getProductDelivery().deliveryPeriodAverage(),
                    document.getProductDelivery().returnMethodDomestic(),
                    document.getProductDelivery().returnCourierDomestic(),
                    document.getProductDelivery().returnChargeDomestic(),
                    document.getProductDelivery().returnExchangeAreaDomestic()
            ));

            productGroupBatchInsertEntities.addNotice(new ProductNoticeEntity(
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


            productGroupBatchInsertEntities.addDetailDescription(new ProductDetailDescriptionEntity(
                    productGroupId,
                    document.getDetailDescription().detailDescription()
            ));

            document.getProductImages().forEach(image ->
                    productGroupBatchInsertEntities.addImages(new ProductGroupImageEntity(
                            productGroupId,
                            image.productImageType(),
                            image.imageUrl(),
                            image.originUrl()
                    ))
            );

            productGroupBatchInsertEntities.addProductGroupConfig(new ProductGroupConfigEntity(productGroupId, true));

            List<ProductCommand> allProductCommands = document.getProducts().stream()
                    .map(product ->
                            new ProductCommand(
                                    null,
                                    product.soldOutYn(),
                                    product.displayYn(),
                                    product.quantity(),
                                    product.additionalPrice(),
                                    toOptionCommands(product.options()),
                                    false
                            )
                    ).toList();

            productBatchInserts.add(new ProductBatchInsertData(productGroupId, allProductCommands));
        });

        return new ProductGroupBatchInsertData(sellerIds, productGroupIds, productGroupBatchInsertEntities, productBatchInserts);
    }

    private List<OptionCommand> toOptionCommands(List<OptionDocument> optionDocuments){
        return optionDocuments.stream().map(o -> new OptionCommand(o.optionName(), o.optionValue())).toList();
    }

}
