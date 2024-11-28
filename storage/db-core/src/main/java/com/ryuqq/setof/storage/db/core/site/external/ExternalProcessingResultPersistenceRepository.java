package com.ryuqq.setof.storage.db.core.site.external;

import java.util.List;

public interface ExternalProcessingResultPersistenceRepository {

    void saveAllExternalProcessingResult(List<ExternalProcessingResultEntity> processingResultEntities);
}
