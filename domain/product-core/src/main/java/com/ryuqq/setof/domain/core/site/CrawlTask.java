package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.core.ActionType;
import com.ryuqq.setof.core.TaskType;

public class CrawlTask {
    private long endpointId;
    private int stepOrder;
    private TaskType taskType;
    private String actionTarget;
    private ActionType actionType;
    private String params;
    private String responseMapping;


    public CrawlTask(long endpointId, int stepOrder, TaskType taskType, String actionTarget, ActionType actionType, String params, String responseMapping) {
        this.endpointId = endpointId;
        this.stepOrder = stepOrder;
        this.taskType = taskType;
        this.actionTarget = actionTarget;
        this.actionType = actionType;
        this.params = params;
        this.responseMapping = responseMapping;
    }

    public long getEndpointId() {
        return endpointId;
    }

    public int getStepOrder() {
        return stepOrder;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public String getActionTarget() {
        return actionTarget;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getParams() {
        return params;
    }

    public String getResponseMapping() {
        return responseMapping;
    }
}
