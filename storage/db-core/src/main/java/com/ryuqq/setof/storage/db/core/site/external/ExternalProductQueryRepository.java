package com.ryuqq.setof.storage.db.core.site.external;

import java.util.List;

public interface ExternalProductQueryRepository {
    List<Long> fetchUnlinkedProductGroupIdsBySellerIdAndSiteId(long sellerId, List<Long> siteIds);

}
