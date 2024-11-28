package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductProcessingResultDto;

import java.util.Optional;

public interface ExternalProcessingQueryRepository {

    Optional<ExternalProductProcessingResultDto> fetchByProductGroupIdAndDataType(long productGroupId, ProductDataType productDataType);
}
