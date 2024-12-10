package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;

public interface ExternalMallContextMapper {
    SiteName getSiteName();
    ExternalMallProductContext.Builder toExternalMallProductContextBuilder(ExternalMallPreProductContext externalMallPreProductContext);
}
