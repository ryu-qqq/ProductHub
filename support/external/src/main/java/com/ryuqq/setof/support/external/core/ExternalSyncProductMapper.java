package com.ryuqq.setof.support.external.core;


import com.ryuqq.setof.enums.core.SiteName;

public interface ExternalSyncProductMapper {

    SiteName getSiteName();
    ExternalMallRegistrationRequest transform(ExternalMallProductContext externalMallProductContext);

}
