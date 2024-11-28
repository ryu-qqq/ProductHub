package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupConfigDto;

import java.util.List;

public interface ProductGroupConfigQueryRepository {
    List<ProductGroupConfigDto> fetchByConfigIds(List<Long> configIds);
    List<ProductGroupConfigDto> fetchByProductGroupIds(List<Long> configIds);

}
