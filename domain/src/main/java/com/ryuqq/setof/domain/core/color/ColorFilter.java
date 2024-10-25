package com.ryuqq.setof.domain.core.color;

import java.util.List;

public record ColorFilter(
        List<Long> colorIds,
        Long cursorId,
        int pageSize
) {}
