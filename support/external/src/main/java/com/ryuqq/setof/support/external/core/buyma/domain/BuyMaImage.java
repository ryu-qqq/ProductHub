package com.ryuqq.setof.support.external.core.buyma.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaImage(
        @JsonProperty("path")
        String path,
        @JsonProperty("position")
        int position
) {}
