package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public record ExternalProductContext(
        ExternalProduct externalProduct,
        ExternalProductPolicy externalProductPolicy,
        List<ExternalProductProcessingResult> externalProductProcessingResults
) {
    public long getProductGroupId(){
        return externalProduct.productGroupId();
    }
}
