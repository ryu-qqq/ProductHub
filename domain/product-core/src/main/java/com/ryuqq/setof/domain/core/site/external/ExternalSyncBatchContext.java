package com.ryuqq.setof.domain.core.site.external;


import java.util.List;

public record ExternalSyncBatchContext(
        ExternalPolicyContext externalPolicyContext,
        List<ProductPreExternalSyncContext> syncData,
        List<ExternalProduct> failProducts



) {}
