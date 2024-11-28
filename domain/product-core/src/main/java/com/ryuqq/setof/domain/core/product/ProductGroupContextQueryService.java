package com.ryuqq.setof.domain.core.product;

import java.util.List;

public interface ProductGroupContextQueryService {
    ProductGroupContext fetchProductGroupContextById(long productGroupId);
    List<ProductGroupContext> fetchProductGroupContextsByFilter(ProductGroupFilter productGroupFilter);
    long countProductContextByFilter(ProductGroupFilter productGroupFilter);

}
