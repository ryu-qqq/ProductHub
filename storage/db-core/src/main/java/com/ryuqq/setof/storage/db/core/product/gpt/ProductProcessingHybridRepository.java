package com.ryuqq.setof.storage.db.core.product.gpt;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductProcessingHybridRepository implements ProductProcessingResultPersistenceRepository {

    private final ProductProcessingResultJdbcRepository externalProductJdbcRepository;

    public ProductProcessingHybridRepository(ProductProcessingResultJdbcRepository externalProductJdbcRepository) {
        this.externalProductJdbcRepository = externalProductJdbcRepository;
    }

    @Override
    public void saveAllExternalProcessingResult(List<ProuctProcessingResultEntity> processingResultEntities) {
        externalProductJdbcRepository.batchInsertExternalProcessingResults(processingResultEntities);
    }
}
