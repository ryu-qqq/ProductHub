package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProcessingHybridRepository implements ExternalProcessingResultPersistenceRepository {

    private final ExternalProcessingResultJdbcRepository externalProductJdbcRepository;

    public ExternalProcessingHybridRepository(ExternalProcessingResultJdbcRepository externalProductJdbcRepository) {
        this.externalProductJdbcRepository = externalProductJdbcRepository;
    }

    @Override
    public void saveAllExternalProcessingResult(List<ExternalProcessingResultEntity> processingResultEntities) {
        externalProductJdbcRepository.batchInsertExternalProcessingResults(processingResultEntities);
    }
}
