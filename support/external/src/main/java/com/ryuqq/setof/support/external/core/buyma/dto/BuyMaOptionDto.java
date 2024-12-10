package com.ryuqq.setof.support.external.core.buyma.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaOptionDto(
        @JsonProperty("type")
        String type,
        @JsonProperty("value")
        String value,
        @JsonProperty("position")
        int position,
        @JsonProperty("master_id")
        long masterId
) {}
