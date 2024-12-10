package com.ryuqq.setof.support.external.core.buyma.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record BuyMaOptionPayload(
        @JsonProperty("reference_number") String referenceNumber,
        @JsonProperty("variants") List<BuyMaVariantDto> variants
) {
}
