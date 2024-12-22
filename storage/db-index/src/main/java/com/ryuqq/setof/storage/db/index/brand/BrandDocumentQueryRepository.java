package com.ryuqq.setof.storage.db.index.brand;

import com.ryuqq.setof.storage.db.index.brand.dto.BrandIndexFilterDto;

import java.util.List;
import java.util.Optional;

@Deprecated
public interface BrandDocumentQueryRepository {

    boolean existById(long brandId);
    Optional<BrandDocument> fetchById(long brandId);
    List<Long> fetchByBrandName(String brandName);
    List<BrandDocument> fetchByFilter(BrandIndexFilterDto brandIndexFilterDto);
    long countByFilter(BrandIndexFilterDto brandFilter);

}
