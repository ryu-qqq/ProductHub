package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductContextDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductFilterDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductPolicyDto;

import java.util.List;

public interface ExternalProductPolicyQueryRepository {
    List<ExternalProductPolicyDto> fetchByFilter(List<Long> siteIds);
    List<ExternalProductContextDto> fetchExternalProductContextByFilter(ExternalProductFilterDto externalProductFilterDto);

}
