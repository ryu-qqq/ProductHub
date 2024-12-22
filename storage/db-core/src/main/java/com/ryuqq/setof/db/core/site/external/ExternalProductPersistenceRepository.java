package com.ryuqq.setof.storage.db.core.site.external;

import java.util.List;

public interface ExternalProductPersistenceRepository {
    void saveAll(List<ExternalProductEntity> externalProductEntities);
    void updateAll(List<ExternalProductEntity> externalProductEntities);

}
