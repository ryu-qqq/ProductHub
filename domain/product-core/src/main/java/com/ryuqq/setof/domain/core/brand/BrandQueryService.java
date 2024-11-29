package com.ryuqq.setof.domain.core.brand;

import java.util.List;

public interface BrandQueryService {
    boolean existById(long brandId);
    Brand fetchBrand(long brandId);
    List<Brand> fetchBrandsByFilter(BrandFilter brandFilter);
    List<Brand> fetchBrandsByIds(List<Long> brandIds);
    long countByFilter(BrandFilter brandFilter);

}
