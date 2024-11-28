package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.Optional;

public interface ExternalProductProcessingResultQueryService {
    Optional<ExternalProductProcessingResult> findExternalProductProcessing(long productGroupId, ProductDataType productDataType);
}
