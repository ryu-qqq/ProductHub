package com.ryuqq.setof.support.external.core;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalMallSyncService {

    private final StepExecutionManager stepExecutionManager;
    private final SyncResultClassifier resultClassifier;
    private final SiteStepHandlerProvider siteStepHandlerProvider;

    public ExternalMallSyncService(StepExecutionManager stepExecutionManager, SyncResultClassifier resultClassifier, SiteStepHandlerProvider siteStepHandlerProvider) {
        this.stepExecutionManager = stepExecutionManager;
        this.resultClassifier = resultClassifier;
        this.siteStepHandlerProvider = siteStepHandlerProvider;
    }

    public SyncResultSummary sync(List<ExternalMallPreProductContext> externalMallPreProductContexts) {
        stepExecutionManager.resetTooManyRequestsFlag();

        List<DetailedSyncResult> detailedResults = externalMallPreProductContexts.stream()
                .takeWhile(context -> !stepExecutionManager.hasTooManyRequests())
                .map(this::handleSync)
                .toList();

        List<SyncStepResult> success = resultClassifier.classifySuccess(detailedResults);
        List<SyncStepResult> failure = resultClassifier.classifyFailure(detailedResults);

        return new SyncResultSummary(success, failure);
    }

    private DetailedSyncResult handleSync(ExternalMallPreProductContext context) {
        List<SyncStepHandler> stepHandlers = siteStepHandlerProvider.get(context.siteName());
        return stepExecutionManager.executeSteps(context, stepHandlers);
    }


}
