package com.ryuqq.setof.support.external.core.sellic;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SellicResponse(
            String result,
            String message,
            @JsonProperty("product_id")
            String productId
) {}