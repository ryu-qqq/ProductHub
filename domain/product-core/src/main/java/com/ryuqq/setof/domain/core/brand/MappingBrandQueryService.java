package com.ryuqq.setof.domain.core.brand;

import java.util.List;

public interface MappingBrandQueryService {

    List<MappingBrand> getMappingBrands(long siteId, List<Long> brandIds);

}
