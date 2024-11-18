package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.ProductDetailDescriptionCommand;
import jakarta.validation.constraints.NotBlank;

public record ProductGroupDetailDescriptionRequestDto(

        @NotBlank(message = "Detail Description cannot be blank.")
        String detailDescription
) {

    public ProductDetailDescriptionCommand toProductDetailDescription(){
        return new ProductDetailDescriptionCommand(detailDescription);
    }

}
