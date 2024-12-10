package com.ryuqq.setof.support.external.core.buyma.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaImageDto(
        @JsonProperty("path")
        String path,
        @JsonProperty("position")
        int position
) {
}
