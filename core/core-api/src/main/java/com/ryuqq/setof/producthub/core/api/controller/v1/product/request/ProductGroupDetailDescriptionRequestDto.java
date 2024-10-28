package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.command.ProductDetailDescriptionCommand;

public record ProductGroupDetailDescriptionRequestDto(
        String detailDescription
) {

    public ProductGroupDetailDescriptionRequestDto {
        validateFields(detailDescription);
    }

    private void validateFields(String detailDescription) {
        if(detailDescription == null){
            throw new IllegalArgumentException("Detail Description cannot be null");
        }
    }


    public ProductDetailDescriptionCommand toProductDetailDescription(){
        return new ProductDetailDescriptionCommand(detailDescription);
    }

}
