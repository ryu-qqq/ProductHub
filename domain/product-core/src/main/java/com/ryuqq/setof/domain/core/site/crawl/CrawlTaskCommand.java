package com.ryuqq.setof.domain.core.site.crawl;

import com.ryuqq.setof.enums.core.ActionType;
import com.ryuqq.setof.enums.core.ProcessType;
import com.ryuqq.setof.storage.db.core.site.crawl.CrawlTaskEntity;

public record CrawlTaskCommand(
        int stepOrder,
        ProcessType type,
        String target,
        ActionType action,
        String params,
        String endPointUrl,
        String responseMapping
)
{
    public CrawlTaskEntity toCrawlTaskEntity(long endpointId) {
        return new CrawlTaskEntity(endpointId, stepOrder, type, target, action, params, endPointUrl, responseMapping);
    }

}
