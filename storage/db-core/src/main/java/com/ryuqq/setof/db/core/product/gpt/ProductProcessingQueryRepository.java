package com.ryuqq.setof.storage.db.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductDataType;
import com.ryuqq.setof.storage.db.core.product.dto.ProductProcessingResultDto;

import java.util.List;
import java.util.Optional;

public interface ProductProcessingQueryRepository {

    Optional<ProductProcessingResultDto> fetchByProductGroupIdAndDataType(long productGroupId, ProductDataType productDataType);
    List<ProductProcessingResultDto> fetchByProductGroupIdsAndDataType(List<Long> productGroupIds, ProductDataType productDataType);
}
