package com.ryuqq.setof.storage.db.core.product.gpt;

import java.util.List;

public interface ProductProcessingResultPersistenceRepository {

    void saveAllExternalProcessingResult(List<ProuctProcessingResultEntity> processingResultEntities);
}
