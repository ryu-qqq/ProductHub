package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalPolicyDto;

import java.util.List;

public interface ExternalPolicyQueryRepository {

    List<ExternalPolicyDto> fetchBySiteId(List<Long> siteIds);

}
