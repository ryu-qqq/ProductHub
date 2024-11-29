package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.EntityType;
import com.ryuqq.setof.enums.core.RequestType;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.site.external.ExternalRequestEntity;

public record ExternalRequestCommand(
        String transactionId,
        RequestType requestType,
        long siteId,
        EntityType entityType,
        long entityId,
        SyncStatus status,
        String requestBody
) {

    public ExternalRequestEntity toEntity(){
        return new ExternalRequestEntity(transactionId, requestType, siteId, entityType, entityId, status ,requestBody);
    }

}
