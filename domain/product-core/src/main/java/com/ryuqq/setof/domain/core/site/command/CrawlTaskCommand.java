package com.ryuqq.setof.domain.core.site.command;

import com.ryuqq.setof.core.ActionType;
import com.ryuqq.setof.core.TaskType;
import com.ryuqq.setof.storage.db.core.site.CrawlTaskEntity;

public record CrawlTaskCommand(
        int stepOrder,
        TaskType taskType,
        String actionTarget,
        ActionType actionType,
        String params,
        String responseMapping
)
{
    public CrawlTaskEntity toCrawlTaskEntity(long endpointId) {
        return new CrawlTaskEntity(endpointId, stepOrder, taskType, actionTarget, actionType, params, responseMapping);
    }

}
