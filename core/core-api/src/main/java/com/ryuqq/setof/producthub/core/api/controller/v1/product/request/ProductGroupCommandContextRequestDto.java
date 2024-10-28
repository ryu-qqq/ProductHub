package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductImageType;
import com.ryuqq.setof.domain.core.product.command.*;

import java.util.List;

import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.validateListNotNullOrEmpty;
import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.validateObjectNotNull;

public record ProductGroupCommandContextRequestDto(
        ProductGroupInsertRequestDto productGroup,
        ProductNoticeInsertRequestDto productNotice,
        ProductDeliveryRequestDto productDelivery,
        List<ProductGroupImageRequestDto> productImageList,
        ProductGroupDetailDescriptionRequestDto detailDescription,
        List<ProductInsertRequestDto> productOptions
) {
    public ProductGroupCommandContextRequestDto{
        validateFields(productGroup, productNotice, productDelivery, productImageList, detailDescription, productOptions);
    }

    private void validateFields(ProductGroupInsertRequestDto productGroup,
                                                ProductNoticeInsertRequestDto productNotice,
                                                ProductDeliveryRequestDto productDelivery,
                                                List<ProductGroupImageRequestDto> productImageList,
                                                ProductGroupDetailDescriptionRequestDto detailDescription,
                                                List<ProductInsertRequestDto> productOptions){
        validateObjectNotNull(productGroup, "Product Group");
        validateObjectNotNull(productNotice, "Product Notice");
        validateObjectNotNull(productDelivery, "Product Delivery");
        validateObjectNotNull(detailDescription, "Product Detail Description");
        validateListNotNullOrEmpty(productImageList, "Product Image List", false);
        validateListNotNullOrEmpty(productOptions, "Product Options", false);
    }


    public ProductGroupCommandContext toProductGroupCommandContext(){
        ProductGroupCommand group = productGroup.toProductGroupCommand();
        ProductNoticeCommand notice = productNotice.toProductNotice();
        ProductDeliveryCommand delivery = productDelivery.toProductDelivery();
        List<ProductGroupImageCommand> images = toProductGroupImageCommands();
        ProductDetailDescriptionCommand description = detailDescription.toProductDetailDescription();
        List<ProductCommand> productCommands = toProductCommands(group.optionType(), productOptions);

        return new ProductGroupCommandContext(group, notice, delivery, images, description, productCommands);
    }

    public List<ProductCommand> toProductCommands(OptionType optionType, List<ProductInsertRequestDto> productOptions) {
        return productOptions.stream()
                .map(p -> p.toProductCommand(optionType))
                .toList();
    }

    public List<ProductGroupImageCommand> toProductGroupImageCommands() {
        validateProductGroupImages();

        return productImageList.stream()
                .map(ProductGroupImageRequestDto::toProductGroupImage)
                .toList();
    }

    private void validateProductGroupImages() {
        long mainCount = productImageList.stream()
                .map(ProductGroupImageRequestDto::productImageType)
                .filter(type -> type == ProductImageType.MAIN)
                .count();

        if (mainCount == 0) {
            throw new IllegalArgumentException("At least one MAIN image is required.");
        }
        if (mainCount > 1) {
            throw new IllegalArgumentException("Only one MAIN image is allowed.");
        }
    }

}
