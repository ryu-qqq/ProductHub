package com.ryuqq.setof.producthub.core.api.controller.v1.site.response;

import com.ryuqq.setof.core.ActionType;
import com.ryuqq.setof.core.ProcessType;
import com.ryuqq.setof.domain.core.site.CrawlTask;

public record CrawlTaskResponse(
        long endpointId,
        int stepOrder,
        ProcessType type,
        String target,
        ActionType action,
        String params,
        String endPointUrl,
        String responseMapping
) {

    public static CrawlTaskResponse of(CrawlTask crawlTask){
        return new CrawlTaskResponse(
                crawlTask.getEndpointId(),
                crawlTask.getStepOrder(),
                crawlTask.getType(),
                crawlTask.getTarget(),
                crawlTask.getAction(),
                crawlTask.getParams(),
                crawlTask.getEndPointUrl(),
                crawlTask.getResponseMapping()
        );
    }
}
