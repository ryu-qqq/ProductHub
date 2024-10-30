package com.ryuqq.setof.producthub.core.api.controller.v1.color.request;

import com.ryuqq.setof.domain.core.color.ColorFilter;

import java.util.List;

public record ColorGetRequestDto(
        List<Long> colorIds,
        Long cursorId,
        Integer pageSize
) {

    public ColorFilter toColorFilter() {
        int defaultSize = (pageSize == null || pageSize == 0) ? 20 : pageSize;
        return new ColorFilter(colorIds, cursorId, defaultSize);
    }
}
