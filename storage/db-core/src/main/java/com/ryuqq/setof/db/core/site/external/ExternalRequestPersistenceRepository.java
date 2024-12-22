package com.ryuqq.setof.storage.db.core.site.external;

import java.util.List;

public interface ExternalRequestPersistenceRepository {

    void saveAllExternalRequest(List<ExternalRequestEntity> externalRequestEntities);
}
