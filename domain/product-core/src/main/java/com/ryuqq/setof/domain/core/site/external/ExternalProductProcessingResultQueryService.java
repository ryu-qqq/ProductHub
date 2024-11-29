package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.List;
import java.util.Optional;

public interface ExternalProductProcessingResultQueryService {

    Optional<ExternalProductProcessingResult> fetchByProductGroupIdAndDataType(long productGroupId, ProductDataType productDataType);
    List<ExternalProductProcessingResult> fetchByProductGroupIdsAndDataType(List<Long> productGroupIds, ProductDataType productDataType);

}
