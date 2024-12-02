package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public interface ExternalProductQueryService {

    long countByFilter(ExternalProductFilter externalProductFilter);
    List<Long> fetchUnlinkedProductGroupIds(long sellerId, List<Long> siteIds);
    List<ExternalProduct> fetchByFilter(ExternalProductFilter filter);

}
