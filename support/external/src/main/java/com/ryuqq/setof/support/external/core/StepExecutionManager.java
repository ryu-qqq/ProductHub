package com.ryuqq.setof.support.external.core;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class StepExecutionManager {

    private final static int TOO_MANY_REQUEST_STATUS = 429;
    private final static int BAD_REQUEST_STATUS = 400;

    private final AtomicBoolean tooManyRequestsFlag;

    public StepExecutionManager() {
        this.tooManyRequestsFlag = new AtomicBoolean(false);
    }

    public DetailedSyncResult executeSteps(ExternalMallPreProductContext context, List<SyncStepHandler> stepHandlers) {
        DetailedSyncResult detailedResult = context.createDetailedSyncResult();

        for (SyncStepHandler handler : stepHandlers) {
            ExternalMallProductContext.Builder builder = new ExternalMallProductContext.Builder();
            try {
                SyncStepResult result = handler.execute(context, builder);
                detailedResult.addStepResult(result);

                if (!result.isSuccess()) {
                    if (result.getErrorCode() == TOO_MANY_REQUEST_STATUS) {
                        tooManyRequestsFlag.set(true);
                    }
                    break;
                }
            } catch (Exception e) {
                detailedResult.addStepResult(SyncStepResult.failure(handler.getStep(), BAD_REQUEST_STATUS, e.getMessage(), builder, ""));
                break;
            }
        }
        return detailedResult;
    }

    public boolean hasTooManyRequests() {
        return tooManyRequestsFlag.get();
    }

    public void resetTooManyRequestsFlag() {
        tooManyRequestsFlag.set(false);
    }

}
