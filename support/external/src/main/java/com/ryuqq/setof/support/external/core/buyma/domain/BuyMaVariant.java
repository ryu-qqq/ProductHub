package com.ryuqq.setof.support.external.core.buyma.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BuyMaVariant(
        @JsonProperty("options")
        List<BuyMaVariantOption> options,

        @JsonProperty("stock_type")
        String stockType,

        @JsonProperty("stocks")
        Integer stocks
) {}
