package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.EntityType;
import com.ryuqq.setof.enums.core.RequestType;
import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.db.core.site.external.ExternalRequestEntity;

public record ExternalRequestCommand(
        String transactionId,
        RequestType requestType,
        long siteId,
        EntityType entityType,
        SyncStatus syncStatus,
        long entityId,
        int statusValue,
        String statusMessage,
        String requestBody
) {

    public ExternalRequestEntity toEntity(){
        return new ExternalRequestEntity(transactionId, requestType, siteId, entityType, syncStatus, entityId, statusValue, statusMessage, requestBody);
    }

}
