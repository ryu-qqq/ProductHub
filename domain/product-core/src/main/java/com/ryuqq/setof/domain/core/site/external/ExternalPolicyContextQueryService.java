package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public interface ExternalPolicyContextQueryService {

    ExternalPolicyContext fetchById(long siteId);
    List<ExternalPolicyContext> fetchByIds(List<Long> siteIds);

}
