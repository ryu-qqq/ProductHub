package com.ryuqq.setof.storage.db.core.category;

import java.util.List;

public interface MappingCategoryPersistenceRepository {

    void saveAll(List<MappingCategoryEntity> mappingCategoryEntities);
}
