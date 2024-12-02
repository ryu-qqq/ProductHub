package com.ryuqq.setof.support.external.core.buyma.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaOption(
        @JsonProperty("type")
        String type,
        @JsonProperty("value")
        String value,
        @JsonProperty("position")
        int position,
        @JsonProperty("master_id")
        long masterId
){}
