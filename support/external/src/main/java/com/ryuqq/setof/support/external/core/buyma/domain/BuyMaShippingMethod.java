package com.ryuqq.setof.support.external.core.buyma.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BuyMaShippingMethod(
        @JsonProperty("shipping_method_id")
        int shippingMethodId
) {}