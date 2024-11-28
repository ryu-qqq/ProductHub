package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public interface ExternalProductQueryService {

    List<Long> findUnlinkedProductGroupIds(long sellerId, List<Long> siteIds);
    List<ExternalProductContext> findExternalProductContext(ExternalProductFilter externalProductFilter);
}
