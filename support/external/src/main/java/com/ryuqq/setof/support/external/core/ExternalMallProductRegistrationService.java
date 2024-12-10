package com.ryuqq.setof.support.external.core;


import com.ryuqq.setof.enums.core.SiteName;

public interface ExternalMallProductRegistrationService {

    SyncResult registration(ExternalMallPreProductContext context, ExternalMallProductContext externalMallProductContext);
    SiteName getSiteName();

}
