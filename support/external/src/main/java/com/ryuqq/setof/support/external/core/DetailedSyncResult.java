package com.ryuqq.setof.support.external.core;

import java.util.ArrayList;
import java.util.List;

public class DetailedSyncResult {

    private final long productGroupId;
    private final long siteId;
    private final List<SyncStepResult> stepResults;

    public DetailedSyncResult(long productGroupId, long siteId) {
        this.productGroupId = productGroupId;
        this.siteId = siteId;
        this.stepResults = new ArrayList<>();
    }

    public void addStepResult(SyncStepResult result) {
        this.stepResults.add(result);
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getSiteId() {
        return siteId;
    }

    public List<SyncStepResult> getStepResults() {
        return stepResults;
    }

    public boolean isOverallSuccess() {
        return stepResults.stream().allMatch(SyncStepResult::isSuccess);
    }

    public SyncStepResult getLastStepResult() {
        return stepResults.isEmpty() ? null : stepResults.getLast();
    }
}
