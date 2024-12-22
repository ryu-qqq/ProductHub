package com.ryuqq.setof.domain.core.product;

import java.util.List;

public interface ProductGroupQueryService {

    List<Long> fetchIdsByFilter(ProductGroupFilter productGroupFilter);
    ProductGroup fetchById(long productGroupId);
    List<ProductGroup> fetchProductGroupsByFilter(ProductGroupFilter productGroupFilter);
    long countByFilter(ProductGroupFilter productGroupFilter);
    List<ProductGroupInsertRequest> fetchProductGroupInsertRequestsByIds(List<Long> productGroupIds);

}
