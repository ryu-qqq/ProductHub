package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.ProductGroupContextQueryService;
import org.springframework.stereotype.Component;

@Component
public class ProductPreExternalSyncContextFinder {

    private final ProductGroupContextQueryService productGroupContextQueryService;


    public ProductPreExternalSyncContextFinder(ProductGroupContextQueryService productGroupContextQueryService) {
        this.productGroupContextQueryService = productGroupContextQueryService;
    }




}
