package com.ryuqq.setof.storage.db.core.site.external;

import java.util.List;

public interface ExternalSiteSellerPersistenceRepository {

    void insert(ExternalSiteSellerEntity externalSiteSellerEntity);
    void inserts(List<ExternalSiteSellerEntity> externalSiteSellerEntities);

}
