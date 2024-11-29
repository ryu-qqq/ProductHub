package com.ryuqq.setof.support.external.core.oco.client.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record OcoResponseStatus(
        int shop_acid,
        String returnMessage,
        String statusCode
) {
}
