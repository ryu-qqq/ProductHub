package com.ryuqq.setof.producthub.core.api.controller.v1.color.request;

import com.ryuqq.setof.domain.core.color.ColorFilter;

import java.util.List;

public class ColorGetRequestDto {
    private List<Long> colorIds;
    private Long cursorId;
    private int pageSize;

    protected ColorGetRequestDto() {}

    public ColorGetRequestDto(List<Long> colorIds, Long cursorId, int pageSize) {
        this.colorIds = colorIds;
        this.cursorId = cursorId;
        this.pageSize = pageSize;
    }

    public ColorFilter toColorFilter() {
        return new ColorFilter(colorIds, cursorId, pageSize);
    }
}
