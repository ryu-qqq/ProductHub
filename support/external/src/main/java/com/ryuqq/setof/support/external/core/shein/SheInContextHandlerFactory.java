package com.ryuqq.setof.support.external.core.shein;

import com.ryuqq.setof.support.external.core.SyncStepHandler;

import java.util.List;

public class SheInContextHandlerFactory {

    public static List<SyncStepHandler> createHandlers() {
        return List.of(
//                new TransformProductStepHandler(),
//                new TransformOptionStepHandler(),
//                new RequestStepHandler()
        );
    }

}
