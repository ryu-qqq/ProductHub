package com.ryuqq.setof.support.external.core.oco.client.payload;

public record OcoResponse<T>(
        T apiResult,
         OcoPage pageSet,
         OcoResponseStatus responseStatus
) {


}