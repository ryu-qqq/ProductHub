package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.core.ActionType;
import com.ryuqq.setof.core.TaskType;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "CRAWL_TASK")
@Entity
public class CrawlTaskEntity extends BaseEntity {

    @Column(name = "ENDPOINT_ID", nullable = false)
    private long endpointId;

    @Column(name = "STEP_ORDER", nullable = false)
    private int stepOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "TASK_TYPE", nullable = true, length = 20)
    private TaskType taskType;

    @Column(name = "ACTION_TARGET", nullable = true, length = 255)
    private String actionTarget;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION_TYPE", nullable = true, length = 20)
    private ActionType actionType;

    @Column(name = "PARAMS", nullable = true)
    private String params;

    @Column(name = "RESPONSE_MAPPING", nullable = true)
    private String responseMapping;

    protected CrawlTaskEntity() {}

    public CrawlTaskEntity(long endpointId, int stepOrder, TaskType taskType, String actionTarget, ActionType actionType, String params, String responseMapping) {
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
