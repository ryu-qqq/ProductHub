package com.ryuqq.setof.storage.db.core.color.dto;

import java.util.List;

public record ColorStorageFilterDto(
        List<Long> colorIds,
        Long cursorId,
        int pageSize
) {}
