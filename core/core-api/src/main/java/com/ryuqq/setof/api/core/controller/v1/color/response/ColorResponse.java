package com.ryuqq.setof.api.core.controller.v1.color.response;

import com.ryuqq.setof.domain.core.color.ColorRecord;

public record ColorResponse(
        long colorId,
        String colorName
) {
    public static ColorResponse of(ColorRecord colorRecord) {
        return new ColorResponse(colorRecord.id(), colorRecord.colorName());
    }
}
