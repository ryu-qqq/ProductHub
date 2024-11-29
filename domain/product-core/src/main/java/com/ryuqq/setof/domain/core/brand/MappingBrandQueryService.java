package com.ryuqq.setof.domain.core.brand;

import java.util.List;

public interface MappingBrandQueryService {

    List<MappingBrand> fetchBySiteIdAndBrandIds(long siteId, List<Long> brandIds);

}
