package com.ryuqq.setof.storage.db.core.site;

import com.ryuqq.setof.enums.core.ActionType;
import com.ryuqq.setof.enums.core.ProcessType;
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
    @Column(name = "PROCESS_TYPE", nullable = true, length = 20)
    private ProcessType processType;

    @Column(name = "ACTION_TARGET", nullable = true, length = 255)
    private String actionTarget;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION_TYPE", nullable = true, length = 20)
    private ActionType actionType;

    @Column(name = "PARAMS", nullable = true)
    private String params;

    @Column(name = "ENDPOINT_URL", nullable = true)
    private String endPointUrl;

    @Column(name = "RESPONSE_MAPPING", nullable = true)
    private String responseMapping;

    protected CrawlTaskEntity() {}

    public CrawlTaskEntity(long endpointId, int stepOrder, ProcessType processType, String actionTarget, ActionType actionType, String params, String endPointUrl, String responseMapping) {
        this.endpointId = endpointId;
        this.stepOrder = stepOrder;
        this.processType = processType;
        this.actionTarget = actionTarget;
        this.actionType = actionType;
        this.params = params;
        this.endPointUrl = endPointUrl;
        this.responseMapping = responseMapping;
    }

    public void delete(){
        super.delete();
    }
}
