package com.ryuqq.setof.domain.core.product;

import java.util.ArrayList;
import java.util.List;

public class UpdateDecision {

    private final List<UpdateDomain<?>> realTimeUpdates = new ArrayList<>();
    private final List<UpdateDomain<?>> batchUpdates = new ArrayList<>();

    public void addRealTimeUpdate(Object entity) {
        realTimeUpdates.add(new UpdateDomain<>(entity, true));
    }

    public void addBatchUpdate(Object entity) {
        batchUpdates.add(new UpdateDomain<>(entity, false));
    }

    public boolean hasRealTimeUpdates() {
        return !realTimeUpdates.isEmpty();
    }

    public boolean hasBatchUpdates() {
        return !batchUpdates.isEmpty();
    }

    public List<UpdateDomain<?>> getRealTimeUpdates() {
        return realTimeUpdates;
    }

    public List<UpdateDomain<?>> getBatchUpdates() {
        return batchUpdates;
    }

    public void merge(UpdateDecision other) {
        realTimeUpdates.addAll(other.getRealTimeUpdates());
        batchUpdates.addAll(other.getBatchUpdates());
    }

}
