package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductPolicyDto;

import java.util.List;

public interface ExternalProductPolicyQueryRepository {
    List<ExternalProductPolicyDto> fetchBySiteIds(List<Long> siteIds);
}
