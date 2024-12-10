package com.ryuqq.setof.support.external.core.buyma.dto;

import java.util.List;

public record BuyMaOptionContextDto(
        List<BuyMaOptionDto> buyMaOptions,
        List<BuyMaVariantDto> buyMaVariants,
        String optionComment
){}
