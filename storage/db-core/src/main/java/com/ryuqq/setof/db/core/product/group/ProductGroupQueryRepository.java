package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupDto;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupInsertRequestDto;
import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupStorageFilterDto;

import java.util.List;
import java.util.Optional;

public interface ProductGroupQueryRepository {

    List<Long> fetchIdsByFilter(ProductGroupStorageFilterDto filter);
    Optional<ProductGroupDto> fetchById(long productGroupId);
    List<ProductGroupDto> fetchByIds(List<Long> productGroupIds);
    long countByFilter(ProductGroupStorageFilterDto filter);

    List<ProductGroupInsertRequestDto> fetchInsertRequestDtoByIds(List<Long> productGroupIds);

}
