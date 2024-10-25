package com.ryuqq.setof.domain.core.brand;



import java.util.List;
import java.util.Optional;

public interface BrandQueryRepository  {

    boolean fetchBrandExists(long brandId);
    Optional<Brand> fetchBrand(long brandId);
    List<Brand> fetchBrands(BrandFilter brandFilter);
    long fetchBrandCount(BrandFilter brandFilter);

}
