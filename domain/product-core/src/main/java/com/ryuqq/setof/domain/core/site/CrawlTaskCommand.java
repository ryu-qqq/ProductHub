package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.ActionType;
import com.ryuqq.setof.core.ProcessType;
import com.ryuqq.setof.storage.db.core.site.CrawlTaskEntity;

public record CrawlTaskCommand(
        int stepOrder,
        ProcessType type,
        String target,
        ActionType action,
        String params,
        String responseMapping
)
{
    public CrawlTaskEntity toCrawlTaskEntity(long endpointId) {
        return new CrawlTaskEntity(endpointId, stepOrder, type, target, action, params, responseMapping);
    }

}
