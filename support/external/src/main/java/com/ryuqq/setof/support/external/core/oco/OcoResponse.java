package com.ryuqq.setof.support.external.core.oco;

public record OcoResponse<T>(
            T apiResult,
            OcoPage pageSet,
            OcoResponseStatus responseStatus
) {}