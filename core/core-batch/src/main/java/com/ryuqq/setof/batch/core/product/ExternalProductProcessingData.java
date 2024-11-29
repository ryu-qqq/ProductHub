package com.ryuqq.setof.batch.core.product;

import com.ryuqq.setof.domain.core.product.ProductGroupConfigContext;
import com.ryuqq.setof.domain.core.site.external.ExternalProductPolicy;

import java.util.List;

public record ExternalProductProcessingData(
        ProductGroupConfigContext productGroupConfigContext,
        List<ExternalProductPolicy> externalSiteProductPolicies
) {}
