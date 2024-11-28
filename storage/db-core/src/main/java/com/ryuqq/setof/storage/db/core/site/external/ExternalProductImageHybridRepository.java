package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExternalProductImageHybridRepository implements ExternalProductImagePersistenceRepository {

    private final ExternalProductJpaRepository externalProductJpaRepository;
    private final ExternalProductImageJdbcRepository externalProductImageJdbcRepository;

    public ExternalProductImageHybridRepository(ExternalProductJpaRepository externalProductJpaRepository, ExternalProductImageJdbcRepository externalProductImageJdbcRepository) {
        this.externalProductJpaRepository = externalProductJpaRepository;
        this.externalProductImageJdbcRepository = externalProductImageJdbcRepository;
    }

    @Override
    public void saveExternalProductImage(ExternalProductImageEntity externalProductImageEntity) {
        externalProductJpaRepository.save(externalProductImageEntity);
    }

    @Override
    public void saveExternalProductImage(List<ExternalProductImageEntity> externalProductImageEntities) {
        externalProductImageJdbcRepository.batchInsertExternalProductImages(externalProductImageEntities);
    }

    @Override
    public void updateAllExternalProductImage(List<ExternalProductImageEntity> externalProductImageEntities) {
        int[] rowsAffected = externalProductImageJdbcRepository.batchUpdateExternalProductImages(externalProductImageEntities);
        if(rowsAffected.length != externalProductImageEntities.size()) {

        }
    }

}
