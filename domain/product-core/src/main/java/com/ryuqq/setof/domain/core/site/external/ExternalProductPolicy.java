package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.db.core.site.external.dto.ExternalProductPolicyDto;

public record ExternalProductPolicy(
        long policyId,
        long siteId,
        Origin countryCode,
        boolean translated
)
    {
    public static ExternalProductPolicy from(long policyId, long siteId, ExternalProductPolicyDto dto) {
        return new ExternalProductPolicy(policyId, siteId, dto.getCountryCode(), dto.isTranslated());
    }

    public boolean shouldAddNameConfig() {
            return this.translated() || this.countryCode().equals(Origin.KR);
    }
}
