package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.ProductGroupImageCommand;
import com.ryuqq.setof.enums.core.ProductImageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductGroupImageRequestDto(

        @NotNull(message = "ProductImage Type cannot be null.")
        ProductImageType productImageType,

        @NotBlank(message = "Image Url Name cannot be blank.")
        @Size(max = 255, message = "Image Url must be 255 characters or less.")
        String imageUrl
){
    public ProductGroupImageCommand toProductGroupImage(){
        return new ProductGroupImageCommand(null, productImageType, imageUrl, false);
    }

}
