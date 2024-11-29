package com.ryuqq.setof.api.core.controller.v1.site.response;

import com.ryuqq.setof.domain.core.site.external.ExternalProductContext;
import com.ryuqq.setof.domain.core.site.external.ExternalProduct;
import com.ryuqq.setof.domain.core.site.external.ExternalProductPolicy;
import com.ryuqq.setof.enums.core.Origin;

public record ExternalMallProductPendingDataResponse(
    long siteId,
    long productGroupId,
    long policyId,
    Origin countryCode,
    boolean translatedNeeded
) {

    public static ExternalMallProductPendingDataResponse toResponse(ExternalProductContext externalProductContext){
        ExternalProduct externalProduct = externalProductContext.externalProduct();
        ExternalProductPolicy externalProductPolicy = externalProductContext.externalProductPolicy();

        return new ExternalMallProductPendingDataResponse(
                externalProduct.siteId(),
                externalProduct.productGroupId(),
                externalProduct.policyId(),
                externalProductPolicy.countryCode(),
                externalProductPolicy.translated()
        );
    }

}
