package com.ryuqq.setof.storage.db.core.site;

import java.util.List;

public interface ExternalSiteSellerPersistenceService {

    void insert(ExternalSiteSellerEntity externalSiteSellerEntity);
    void inserts(List<ExternalSiteSellerEntity> externalSiteSellerEntities);

}
