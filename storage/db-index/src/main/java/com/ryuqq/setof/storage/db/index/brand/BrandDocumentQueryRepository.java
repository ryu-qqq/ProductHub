package com.ryuqq.setof.storage.db.index.brand;

import com.ryuqq.setof.storage.db.index.brand.dto.BrandIndexFilterDto;

import java.util.List;
import java.util.Optional;

public interface BrandDocumentQueryRepository {

    boolean fetchBrandExists(long brandId);
    Optional<BrandDocument> fetchBrand(long brandId);
    List<BrandDocument> fetchBrands(BrandIndexFilterDto brandIndexFilterDto);
    long fetchBrandCount(BrandIndexFilterDto brandFilter);
    List<BrandDocument> fetchBrands(List<String> brandNames);

}
