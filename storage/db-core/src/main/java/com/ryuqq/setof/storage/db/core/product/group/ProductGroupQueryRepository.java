package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupContextDto;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupStorageFilterDto;

import java.util.List;
import java.util.Optional;

public interface ProductGroupQueryRepository {

    List<Long> fetchProductGroupIds(ProductStatus productStatus, int pageSize);
    Optional<ProductStatus> fetchProductStatus(long productGroupId);
    Optional<ProductGroupContextDto> fetchProductGroupContext(long productGroup);
    List<ProductGroupContextDto> fetchProductGroupContexts(ProductGroupStorageFilterDto productGroupStorageFilterDto);
    long fetchProductGroupCount(ProductGroupStorageFilterDto filter);
}
