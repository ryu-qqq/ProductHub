package com.ryuqq.setof.support.external.core.buyma.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariant;

import java.util.List;

public record BuyMaOptionPayload(
        @JsonProperty("reference_number") String referenceNumber,
        @JsonProperty("variants") List<BuyMaVariant> variants
) {
}
