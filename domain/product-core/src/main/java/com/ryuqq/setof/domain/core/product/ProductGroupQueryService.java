package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ProductStatus;

import java.util.List;

public interface ProductGroupQueryService {

    ProductStatus findProductStatus(long productGroupId);
    ProductGroupContext findProductGroupContext(long productGroupId);
    List<ProductGroupContext> findProductGroupContexts(ProductGroupFilter productGroupFilter, List<Long> categoryIds);
    long findProductGroupCount(ProductGroupFilter productGroupFilter, List<Long> categoryIds);

}
