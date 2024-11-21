package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductJpaService implements ExternalProductPersistenceService {

    private final ExternalProductJdbcRepository externalProductJdbcRepository;

    public ExternalProductJpaService(ExternalProductJdbcRepository externalProductJdbcRepository) {
        this.externalProductJdbcRepository = externalProductJdbcRepository;
    }

    @Override
    public void insertAll(List<ExternalProductEntity> externalProductEntities) {
        externalProductJdbcRepository.batchInsertExternalProducts(externalProductEntities);
    }

}
