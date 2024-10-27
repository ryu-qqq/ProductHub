package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.ProductImageType;
import com.ryuqq.setof.domain.core.product.command.ProductGroupImageCommand;

import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.validateEnum;
import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.validateString;

public record ProductGroupImageRequestDto(
        ProductImageType productImageType,
        String imageUrl
){
    public ProductGroupImageRequestDto {
        validateFields();
    }

    private void validateFields() {
        validateString(imageUrl, 255, "Image Url");
        validateEnum(productImageType, "Product Image Type");
    }

    public ProductGroupImageCommand toProductGroupImage(){
        return new ProductGroupImageCommand(productImageType, imageUrl);
    }
}
