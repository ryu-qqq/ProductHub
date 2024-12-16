package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public interface ExternalProductGroupQueryService {

    long countByFilter(ExternalProductGroupFilter externalProductGroupFilter);
    List<Long> fetchUnlinkedProductGroupIds(long sellerId, List<Long> siteIds);
    List<ExternalProductGroup> fetchByFilter(ExternalProductGroupFilter filter);

}
