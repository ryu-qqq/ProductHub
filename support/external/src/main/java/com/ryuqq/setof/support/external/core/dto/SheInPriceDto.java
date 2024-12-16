package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record SheInPriceDto(
        @JsonProperty("base_price") BigDecimal basePrice,
        @JsonProperty("currency") String currency,
        @JsonProperty("sub_site") String subSite
) {}
