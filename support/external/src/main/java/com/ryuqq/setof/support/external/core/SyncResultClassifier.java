package com.ryuqq.setof.support.external.core;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SyncResultClassifier {

    public List<SyncStepResult> classifySuccess(List<DetailedSyncResult> detailedResults) {
        return detailedResults.stream()
                .filter(DetailedSyncResult::isOverallSuccess)
                .map(DetailedSyncResult::getLastStepResult)
                .filter(result -> result != null && result.isSuccess())
                .toList();
    }

    public List<SyncStepResult> classifyFailure(List<DetailedSyncResult> detailedResults) {
        return detailedResults.stream()
                .filter(result -> !result.isOverallSuccess())
                .map(DetailedSyncResult::getLastStepResult)
                .toList();
    }
}
