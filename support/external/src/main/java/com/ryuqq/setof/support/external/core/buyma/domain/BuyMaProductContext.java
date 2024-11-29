package com.ryuqq.setof.support.external.core.buyma.domain;


import com.ryuqq.setof.support.external.core.ExternalMallProductImageContext;
import com.ryuqq.setof.support.external.core.ExternalMallProductPayload;

import java.util.List;

public record BuyMaProductContext(
        BuyMaProduct product
)  implements ExternalMallProductPayload {

    @Override
    public List<? extends ExternalMallProductImageContext> getExternalMallImagePayload() {
        return product.getExternalMallProductImageContext();
    }
}
