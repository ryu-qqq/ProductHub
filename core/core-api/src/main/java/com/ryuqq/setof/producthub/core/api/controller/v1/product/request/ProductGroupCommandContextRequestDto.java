package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.*;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.validator.ProductGroupCommandValidate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@ProductGroupCommandValidate
public record ProductGroupCommandContextRequestDto(

        @NotNull(message = "Product Group cannot be null.")
        @Valid
        ProductGroupInsertRequestDto productGroup,

        @NotNull(message = "Product Notice cannot be null.")
        @Valid
        ProductNoticeInsertRequestDto productNotice,

        @NotNull(message = "Product Delivery cannot be null.")
        @Valid
        ProductDeliveryRequestDto productDelivery,

        @NotNull(message = "Product Image List cannot be null.")
        @Size(min = 1, max = 10, message = "Product Image List cannot be empty.")
        @Valid
        List<ProductGroupImageRequestDto> productImageList,

        @NotNull(message = "Product Detail Description cannot be null.")
        @Valid
        ProductGroupDetailDescriptionRequestDto detailDescription,

        @Valid
        List<ProductInsertRequestDto> productOptions
) {

    public ProductGroupCommandContext toProductGroupCommandContext(){
        ProductGroupCommand group = productGroup.toProductGroupCommand();
        ProductNoticeCommand notice = productNotice.toProductNotice();
        ProductDeliveryCommand delivery = productDelivery.toProductDelivery();
        List<ProductGroupImageCommand> images =  productImageList.stream().map(ProductGroupImageRequestDto::toProductGroupImage).toList();
        ProductDetailDescriptionCommand description = detailDescription.toProductDetailDescription();
        List<ProductCommand> productCommands = productOptions.stream().map(ProductInsertRequestDto::toProductCommand).toList();
        return new ProductGroupCommandContext(group, notice, delivery, images, description, productCommands);
    }


}
