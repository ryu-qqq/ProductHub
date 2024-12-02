package com.ryuqq.setof.support.external.core.buyma.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaVariantOption(
        @JsonProperty("type")
        String type,

        @JsonProperty("value")
        String value

){}
