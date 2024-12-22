package com.ryuqq.setof.storage.db.core.site.crawl.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.ActionType;
import com.ryuqq.setof.enums.core.ProcessType;

public class CrawlTaskDto {

    long endpointId;
    private final int stepOrder;
    private final ProcessType type;
    private final String target;
    private final ActionType action;
    private final String params;
    private final String endPointUrl;
    private final String responseMapping;

    @QueryProjection
    public CrawlTaskDto(long endpointId, int stepOrder, ProcessType type, String target, ActionType action, String params, String endPointUrl, String responseMapping) {
        this.endpointId = endpointId;
        this.stepOrder = stepOrder;
        this.type = type;
        this.target = target;
        this.action = action;
        this.params = params;
        this.endPointUrl = endPointUrl;
        this.responseMapping = responseMapping;
    }

    public long getEndpointId() {
        return endpointId;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public ProcessType getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }

    public ActionType getAction() {
        return action;
    }

    public String getParams() {
        return params;
    }

    public String getEndPointUrl() {
        return endPointUrl;
    }

    public String getResponseMapping() {
        return responseMapping;
    }
}
