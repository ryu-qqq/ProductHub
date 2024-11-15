package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupContextDto;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupStorageFilterDto;

import java.util.List;
import java.util.Optional;

public interface ProductGroupQueryRepository {

    Optional<ProductGroupContextDto> fetchProductGroupContext(long productGroup);
    List<ProductGroupContextDto> fetchProductGroupContexts(ProductGroupStorageFilterDto productGroupStorageFilterDto);
    long fetchProductGroupCount(ProductGroupStorageFilterDto filter);
}
