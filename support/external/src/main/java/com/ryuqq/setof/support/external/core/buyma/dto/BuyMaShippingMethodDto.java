package com.ryuqq.setof.support.external.core.buyma.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaShippingMethodDto(
        @JsonProperty("shipping_method_id")
        int shippingMethodId
) {
}
