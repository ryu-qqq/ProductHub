package com.ryuqq.setof.producthub.core.api.controller.v1.color.response;

import com.ryuqq.setof.domain.core.color.ColorRecord;

public record ColorResponse(
        long id,
        String colorName
) {
    public static ColorResponse of(ColorRecord colorRecord) {
        return new ColorResponse(colorRecord.id(), colorRecord.colorName());
    }
}
