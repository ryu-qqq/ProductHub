package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;

import java.util.List;

public interface ExternalProductPersistenceRepository {

    void saveAll(List<ExternalProductEntity> externalProductEntities);
    void updateStatusByProductGroupId(List<Long> productGroupIds, SyncStatus status);
}
