package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.SiteName;

public interface ExternalMallContextBuilder {

    ExternalMallProductContext.Builder getBuilder();
    SiteName getSiteName();

}
