package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.core.ActionType;
import com.ryuqq.setof.core.TaskType;
import com.ryuqq.setof.domain.core.site.CrawlTask;

public record CrawlTaskResponse(
        long endpointId,
        int stepOrder,
        TaskType taskType,
        String actionTarget,
        ActionType actionType,
        String params,
        String responseMapping
) {

    public static CrawlTaskResponse of(CrawlTask crawlTask){
        return new CrawlTaskResponse(
                crawlTask.getEndpointId(),
                crawlTask.getStepOrder(),
                crawlTask.getTaskType(),
                crawlTask.getActionTarget(),
                crawlTask.getActionType(),
                crawlTask.getParams(),
                crawlTask.getResponseMapping()
        );
    }
}
