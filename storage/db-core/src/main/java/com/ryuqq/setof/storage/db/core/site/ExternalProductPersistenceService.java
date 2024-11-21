package com.ryuqq.setof.storage.db.core.site;

import java.util.List;

public interface ExternalProductPersistenceService {

    void insertAll(List<ExternalProductEntity> externalProductEntities);

}
