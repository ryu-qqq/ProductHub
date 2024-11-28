package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupDto;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupStorageFilterDto;

import java.util.List;
import java.util.Optional;

public interface ProductGroupQueryRepository {

    List<Long> fetchIdsByFilter(ProductGroupStorageFilterDto filter);
    List<Long> fetchIdsByStatusAndCursor(ProductStatus productStatus, Long cursorId, int pageSize);
    Optional<ProductGroupDto> fetchById(long productGroupId);
    List<ProductGroupDto> fetchByIds(List<Long> productGroupIds);
    long countByFilter(ProductGroupStorageFilterDto filter);

}
