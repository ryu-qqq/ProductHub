package com.ryuqq.setof.producthub.core.api.controller.v1.color.request;

import com.ryuqq.setof.domain.core.color.ColorFilter;

import java.util.List;

public record ColorGetRequestDto(
        List<Long> colorIds,
        Long cursorId,
        int pageSize
) {

    public ColorFilter toColorFilter() {
        return new ColorFilter(colorIds, cursorId, pageSize);
    }
}
