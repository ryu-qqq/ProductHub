package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.storage.db.core.site.dto.StandardSizeDto;

import java.util.List;

public interface StandardSizeQueryRepository {
    List<StandardSizeDto> fetchByCategoryIds(List<Long> categoryIds);
}
