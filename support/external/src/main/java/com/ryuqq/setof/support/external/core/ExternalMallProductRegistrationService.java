package com.ryuqq.setof.support.external.core;


import com.ryuqq.setof.enums.core.SiteName;

public interface ExternalMallProductRegistrationService<T extends ExternalMallProductPayload> {

    ExternalMallRegistrationResult registration(long productGroupId, long siteId, T t);
    SiteName getSiteName();
}
