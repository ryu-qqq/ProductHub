package com.ryuqq.setof.domain.core.site;

import java.util.List;

public interface StandardSizeQueryService {

    List<StandardSize> fetchByCategoryIds(List<Long> categoryIds);

}
