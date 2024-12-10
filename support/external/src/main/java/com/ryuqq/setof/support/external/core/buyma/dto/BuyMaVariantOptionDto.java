package com.ryuqq.setof.support.external.core.buyma.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaVariantOptionDto(
        @JsonProperty("type")
        String type,
        @JsonProperty("value")
        String value
){}
