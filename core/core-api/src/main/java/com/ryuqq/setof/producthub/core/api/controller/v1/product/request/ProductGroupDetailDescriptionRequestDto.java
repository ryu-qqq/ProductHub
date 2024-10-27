package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.command.ProductDetailDescriptionCommand;

public record ProductGroupDetailDescriptionRequestDto(
        String detailDescription
) {
    public ProductDetailDescriptionCommand toProductDetailDescription(){
        return new ProductDetailDescriptionCommand(detailDescription);
    }

}
