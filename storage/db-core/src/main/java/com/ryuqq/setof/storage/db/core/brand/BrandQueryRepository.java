package com.ryuqq.setof.storage.db.core.brand;

import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandStorageFilterDto;

import java.util.List;
import java.util.Optional;

public interface BrandQueryRepository  {

    boolean fetchBrandExists(long brandId);
    Optional<BrandDto> fetchBrand(long brandId);
    List<BrandDto> fetchBrands(BrandStorageFilterDto brandFilter);
    long fetchBrandCount(BrandStorageFilterDto brandFilter);

}
