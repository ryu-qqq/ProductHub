package com.ryuqq.setof.support.external.core.oco.dto;

import java.util.List;

public record OcoOptionContextDto(
        int optionLength,
        boolean optionProduct,
        List<OcoOptionDto> ocoOptionDto
){

    public int getTotalQuantity(){
        return ocoOptionDto.stream()
                .map(OcoOptionDto::stockQuantity)
                .reduce(0, Integer::sum);
    }

}
