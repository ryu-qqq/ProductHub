package com.ryuqq.setof.support.external.core;


import com.ryuqq.setof.enums.core.SiteName;

public interface ExternalMallProductRegistrationService<T extends ExternalMallProductContext> {

    ExternalMallSyncResponse registration(T externalMallProductContext);
    SiteName getSiteName();
}
