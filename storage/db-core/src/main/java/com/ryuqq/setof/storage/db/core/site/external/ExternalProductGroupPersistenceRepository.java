package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;

import java.util.List;

public interface ExternalProductGroupPersistenceRepository {

    void saveAll(List<ExternalProductGroupEntity> externalProductEntities);
    void updateAll(List<ExternalProductGroupEntity> externalProductEntities);
    void updateStatusByProductGroupId(List<Long> productGroupIds, Long siteId,SyncStatus status);
}
