package com.ryuqq.setof.support.external.core.sellic;

import com.ryuqq.setof.support.external.core.SyncStepHandler;

import java.util.List;

public class SellicContextHandlerFactory {

    public static List<SyncStepHandler> createHandlers() {
        return List.of(
//                new TransformProductStepHandler(),
//                new TransformOptionStepHandler(),
//                new RequestStepHandler()
        );
    }
}
