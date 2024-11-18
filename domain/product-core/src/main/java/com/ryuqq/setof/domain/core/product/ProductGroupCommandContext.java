package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.index.product.*;

import java.util.List;

public record ProductGroupCommandContext(
        ProductGroupCommand productGroupCommand,
        ProductNoticeCommand productNoticeCommand,
        ProductDeliveryCommand productDeliveryCommand,
        List<ProductGroupImageCommand> productGroupImageCommands,
        ProductDetailDescriptionCommand productDetailDescriptionCommand,
        List<ProductCommand> productCommands
) {
    public ProductGroupCommandContextDocument toDocument(long productGroupId) {
        return new ProductGroupCommandContextDocument(
                productGroupId,
                toProductGroupDocument(),
                toNoticeDocument(),
                toDeliveryDocument(),
                toImageDocument(),
                toDetailDescriptionDocument(),
                toProductCommandDocument()
        );
    }

    private ProductGroupCommandDocument toProductGroupDocument(){
        return new ProductGroupCommandDocument(
                productGroupCommand.brandId(),
                productGroupCommand.categoryId(),
                productGroupCommand.sellerId(),
                productGroupCommand.productGroupName(),
                productGroupCommand.styleCode(),
                productGroupCommand.productCondition().name(),
                productGroupCommand.managementType().name(),
                productGroupCommand.optionType().name(),
                productGroupCommand.regularPrice(),
                productGroupCommand.currentPrice(),
                productGroupCommand.soldOutYn(),
                productGroupCommand.displayYn(),
                ProductStatus.WAITING.name(),
                productGroupCommand.keywords()
        );
    }

    private ProductNoticeDocument toNoticeDocument() {
        return new ProductNoticeDocument(
                productNoticeCommand.material(),
                productNoticeCommand.color(),
                productNoticeCommand.size(),
                productNoticeCommand.maker(),
                productNoticeCommand.origin(),
                productNoticeCommand.washingMethod(),
                productNoticeCommand.yearMonth(),
                productNoticeCommand.assuranceStandard(),
                productNoticeCommand.asPhone()
        );
    }

    private ProductDeliveryDocument toDeliveryDocument() {
        return new ProductDeliveryDocument(
                productDeliveryCommand.deliveryArea(),
                productDeliveryCommand.deliveryFee(),
                productDeliveryCommand.deliveryPeriodAverage(),
                productDeliveryCommand.returnMethodDomestic(),
                productDeliveryCommand.returnCourierDomestic(),
                productDeliveryCommand.returnChargeDomestic(),
                productDeliveryCommand.returnExchangeAreaDomestic()
        );

    }

    private List<ProductGroupImageDocument> toImageDocument() {
        return productGroupImageCommands.stream().map(command -> new ProductGroupImageDocument(
                command.productImageType(),
                command.imageUrl(),
                command.imageUrl())
        ).toList();
    }

    private ProductDetailDescriptionDocument toDetailDescriptionDocument() {
        return new ProductDetailDescriptionDocument(productDetailDescriptionCommand().detailDescription());
    }

    private List<ProductCommandDocument> toProductCommandDocument() {
        return productCommands.stream().map(command ->new ProductCommandDocument(
                command.soldOutYn(),
                command.displayYn(),
                command.quantity(),
                command.additionalPrice(),
                toOptionDocuments(command.options())
        )).toList();
    }

    private List<OptionDocument> toOptionDocuments(List<OptionCommand> optionCommands) {
        return optionCommands.stream().map(command -> new OptionDocument(
                command.name(),
                command.value()
        )).toList();
    }

}
