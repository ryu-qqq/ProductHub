package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExtraCategoryOptionHybridRepository implements ExternalCategoryOptionPersistenceRepository{

    private final ExternalCategoryOptionJdbcRepository externalCategoryOptionJdbcRepository;

    public ExtraCategoryOptionHybridRepository(ExternalCategoryOptionJdbcRepository externalCategoryOptionJdbcRepository) {
        this.externalCategoryOptionJdbcRepository = externalCategoryOptionJdbcRepository;
    }

    @Override
    public void saveAll(List<ExternalCategoryOptionEntity> externalCategoryOptionEntities) {
        externalCategoryOptionJdbcRepository.batchInsertExternalCategoryOptions(externalCategoryOptionEntities);
    }
}
