package com.ryuqq.setof.domain.core.color;

import com.ryuqq.setof.storage.db.core.color.dto.ColorStorageFilterDto;

import java.util.List;

public record ColorFilter(
        List<Long> colorIds,
        Long cursorId,
        int pageSize
) {
    public ColorStorageFilterDto toStorageFilter(){
        return new ColorStorageFilterDto(colorIds, cursorId, pageSize);
    }

}
