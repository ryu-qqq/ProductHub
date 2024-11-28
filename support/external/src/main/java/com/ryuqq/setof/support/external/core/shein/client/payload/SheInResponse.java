package com.ryuqq.setof.support.external.core.shein.client.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SheInResponse<T>(
        String code,
        @JsonProperty("msg")
        String message,
        Info<T> info
) {
}
