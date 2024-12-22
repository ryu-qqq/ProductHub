package com.ryuqq.setof.storage.db.core.site.external;

import java.util.List;

public interface ExternalCategoryOptionPersistenceRepository {

    void saveAll(List<ExternalCategoryOptionEntity> externalCategoryOptionEntities);
}
