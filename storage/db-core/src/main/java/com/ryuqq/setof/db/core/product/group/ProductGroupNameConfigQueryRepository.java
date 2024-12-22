package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupNameConfigDto;

import java.util.List;

public interface ProductGroupNameConfigQueryRepository {
    List<ProductGroupNameConfigDto> fetchByProductGroupIds(List<Long> productGroupIds);
}
