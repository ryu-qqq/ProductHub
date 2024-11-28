package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductDto;

public record ExternalProductGroup(
        long id,
        long siteId,
        long productGroupId,
        String externalProductId,
        long policyId,
        SyncStatus status
) {

    public static ExternalProductGroup from(ExternalProductDto dto) {
        return new ExternalProductGroup(
                dto.getId(),
                dto.getSiteId(),
                dto.getProductGroupId(),
                dto.getExternalProductId(),
                dto.getPolicyId(),
                dto.getStatus()
        );
    }
}
