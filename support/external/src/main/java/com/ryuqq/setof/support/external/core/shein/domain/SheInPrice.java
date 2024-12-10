package com.ryuqq.setof.support.external.core.shein.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record SheInPrice(
        @JsonProperty("base_price") BigDecimal basePrice,
        @JsonProperty("currency") String currency,
        @JsonProperty("sub_site") String subSite
) {}
