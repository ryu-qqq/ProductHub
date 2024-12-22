package com.ryuqq.setof.support.external.core.oco.dto;

public record OcoProductUpdateRequestDto(
        int pid,
        OcoProductPayload product
) {
}
