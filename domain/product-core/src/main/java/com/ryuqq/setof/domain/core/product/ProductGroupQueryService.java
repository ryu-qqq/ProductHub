package com.ryuqq.setof.domain.core.product;

import java.util.List;

public interface ProductGroupQueryService {

    List<ProductGroupContext> findProductGroupContexts(ProductGroupFilter productGroupFilter, List<Long> categoryIds);
    long findProductGroupCount(ProductGroupFilter productGroupFilter, List<Long> categoryIds);

}
