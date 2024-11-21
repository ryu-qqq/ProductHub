package com.ryuqq.setof.domain.core.brand;

import java.util.List;

public interface BrandQueryService {
    boolean brandExist(long brandId);
    BrandRecord findBrand(long brandId);
    List<BrandRecord> findBrands(BrandFilter brandFilter);

    long findBrandCount(BrandFilter brandFilter);

}
