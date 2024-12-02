package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.support.utils.JsonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ExternalMallSyncService {

    private final AtomicBoolean tooManyRequestsFlag = new AtomicBoolean(false);

    private final SiteStepHandlerProvider siteStepHandlerProvider;

    public ExternalMallSyncService(SiteStepHandlerProvider siteStepHandlerProvider) {
        this.siteStepHandlerProvider = siteStepHandlerProvider;
    }

    public SyncResultSummary sync(List<ExternalMallPreProductContext> externalMallPreProductContexts) {
        tooManyRequestsFlag.set(false);

        List<DetailedSyncResult> detailedResults = externalMallPreProductContexts.stream()
                .takeWhile(context -> !tooManyRequestsFlag.get())
                .map(this::handleSync)
                .toList();

        List<ExternalMallSyncResponse> successResponses = detailedResults.stream()
                .filter(DetailedSyncResult::isOverallSuccess)
                .map(DetailedSyncResult::getLastStepResult)
                .filter(result -> result != null && result.isSuccess())
                .map(result -> (ExternalMallSyncResponse) result.getResultData())
                .toList();

        List<SyncFailureInfo> failureResponses = detailedResults.stream()
                .filter(result -> !result.isOverallSuccess())
                .map(detailedResult -> {
                    SyncStepResult lastStepResult = detailedResult.getLastStepResult();
                        return new SyncFailureInfo(
                                lastStepResult.getErrorCode(),
                                lastStepResult.getErrorMessage(),
                                lastStepResult.getStep(),
                                detailedResult.getProductGroupId(),
                                JsonUtils.toJson(lastStepResult.getResultData())
                        );
                })
                .toList();

        return new SyncResultSummary(successResponses, failureResponses);
    }

    @SuppressWarnings("unchecked")
    private DetailedSyncResult handleSync(ExternalMallPreProductContext context) {
        DetailedSyncResult detailedResult = context.createDetailedSyncResult();
        List<SyncStepHandler> stepHandlers = siteStepHandlerProvider.get(context.siteName());

        for (SyncStepHandler handler : stepHandlers) {
            try {
                SyncStepResult result = handler.execute(context);
                detailedResult.addStepResult(result);

                if (!result.isSuccess()) {
                    if (result.getErrorCode() == 429) {
                        tooManyRequestsFlag.set(true);
                    }
                    break;
                }

            } catch (Exception e) {
                detailedResult.addStepResult(SyncStepResult.failure(handler.getStep(), 400, e.getMessage(), context));
                break;
            }
        }

        return detailedResult;
    }


}
