package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupContextDto;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupStorageFilterDto;

import java.util.List;

public interface ProductGroupQueryRepository {

    List<ProductGroupContextDto> fetchProductGroupContexts(ProductGroupStorageFilterDto productGroupStorageFilterDto);
    long fetchProductGroupCount(ProductGroupStorageFilterDto filter);
}
