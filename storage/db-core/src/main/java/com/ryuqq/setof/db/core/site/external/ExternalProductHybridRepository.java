package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductHybridRepository implements ExternalProductPersistenceRepository {

    private final ExternalProductJdbcRepository externalProductJdbcRepository;

    public ExternalProductHybridRepository(ExternalProductJdbcRepository externalProductJdbcRepository) {
        this.externalProductJdbcRepository = externalProductJdbcRepository;
    }

    @Override
    public void saveAll(List<ExternalProductEntity> externalProductEntities) {
        externalProductJdbcRepository.batchInsertExternalProducts(externalProductEntities);
    }

    @Override
    public void updateAll(List<ExternalProductEntity> externalProductEntities) {
        List<String> externalProductGroupIds = externalProductEntities.stream()
                .map(ExternalProductEntity::getExternalProductGroupId)
                .toList();

        externalProductJdbcRepository.deleteAll(externalProductGroupIds);
        saveAll(externalProductEntities);
    }

}
