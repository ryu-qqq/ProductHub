package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.ProductGroupContext;

public record ProductPreExternalSyncContext(
        ProductGroupContext productGroupContext,
        ExternalPolicyContext externalPolicyContext
) {}
