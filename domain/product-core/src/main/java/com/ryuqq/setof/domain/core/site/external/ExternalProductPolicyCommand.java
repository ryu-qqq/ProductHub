package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductPolicyEntity;

public record ExternalProductPolicyCommand(
        Origin countryCode,
        boolean translated
) {
    public ExternalProductPolicyEntity toEntity(long policyId){
        return new ExternalProductPolicyEntity(policyId, countryCode, translated);
    }
}
