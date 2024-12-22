package com.ryuqq.setof.storage.db.core.brand;

import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandStorageFilterDto;

import java.util.List;
import java.util.Optional;

public interface BrandQueryRepository  {

    boolean existById(long brandId);
    Optional<BrandDto> fetchById(long brandId);
    List<BrandDto> fetchByFilter(BrandStorageFilterDto brandFilter);
    long countByFilter(BrandStorageFilterDto brandFilter);

}
