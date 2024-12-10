package com.ryuqq.setof.support.external.core.buyma.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BuyMaVariantDto(
        @JsonProperty("options") List<BuyMaVariantOptionDto> options,
        @JsonProperty("stock_type") String stockType,
        @JsonProperty("stocks") Integer stocks
) {
}
