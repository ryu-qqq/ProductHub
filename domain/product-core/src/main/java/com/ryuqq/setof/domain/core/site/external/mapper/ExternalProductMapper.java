package com.ryuqq.setof.domain.core.site.external.mapper;

import com.ryuqq.setof.enums.core.SiteName;

public interface ExternalProductMapper {

    SiteName getSiteName();
    ExternalMallProductPayload transform();

}
