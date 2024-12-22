package com.ryuqq.setof.storage.db.core.site.external;

import java.util.List;

public interface ExternalProductImagePersistenceRepository {

    void saveExternalProductImage(ExternalProductImageEntity externalProductImageEntity);
    void saveExternalProductImage(List<ExternalProductImageEntity> externalProductImageEntities);
    void updateAllExternalProductImage(List<ExternalProductImageEntity> externalProductImageEntities);
}
