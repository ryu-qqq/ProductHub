package com.ryuqq.setof.storage.db.core.category;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MappingCategoryHybridRepository implements MappingCategoryPersistenceRepository{

    private final MappingCategoryJdbcRepository mappingCategoryJdbcRepository;

    public MappingCategoryHybridRepository(MappingCategoryJdbcRepository mappingCategoryJdbcRepository) {
        this.mappingCategoryJdbcRepository = mappingCategoryJdbcRepository;
    }

    @Override
    public void saveAll(List<MappingCategoryEntity> mappingCategoryEntities) {
        mappingCategoryJdbcRepository.batchInsertMappingCategories(mappingCategoryEntities);
    }

}
