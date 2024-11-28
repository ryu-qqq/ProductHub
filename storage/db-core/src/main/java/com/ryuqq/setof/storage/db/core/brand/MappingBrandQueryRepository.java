package com.ryuqq.setof.storage.db.core.brand;

import com.ryuqq.setof.storage.db.core.brand.dto.MappingBrandDto;

import java.util.List;

public interface MappingBrandQueryRepository {

    List<MappingBrandDto> fetchByBrandIdAndSiteId(long siteId, List<Long> brandIds);
}
