package com.ryuqq.setof.api.core.controller.v1.color.response;

import com.ryuqq.setof.domain.core.color.Color;

public record ColorResponse(
        long colorId,
        String colorName
) {
    public static ColorResponse of(Color color) {
        return new ColorResponse(color.id(), color.colorName());
    }
}
