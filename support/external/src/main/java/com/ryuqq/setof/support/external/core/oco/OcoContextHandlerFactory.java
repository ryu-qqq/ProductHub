package com.ryuqq.setof.support.external.core.oco;

import com.ryuqq.setof.support.external.core.SyncStepHandler;

import java.util.List;

public class OcoContextHandlerFactory {

    public static List<SyncStepHandler> createHandlers() {
        return List.of(
//                new TransformProductStepHandler(),
//                new TransformOptionStepHandler(),
//                new RequestStepHandler()
        );
    }
}
