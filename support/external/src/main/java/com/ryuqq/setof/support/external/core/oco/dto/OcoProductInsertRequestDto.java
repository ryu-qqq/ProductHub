package com.ryuqq.setof.support.external.core.oco.dto;

public record OcoProductInsertRequestDto(
        OcoProductPayload product
) {

    public OcoProductUpdateRequestDto toOcoProductUpdateRequestDto(){
        return new OcoProductUpdateRequestDto(product().getPid(), product());
    }
}
