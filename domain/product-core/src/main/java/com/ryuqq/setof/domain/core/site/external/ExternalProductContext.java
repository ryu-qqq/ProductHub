package com.ryuqq.setof.domain.core.site.external;

import java.util.List;

public record ExternalProductContext(
        ExternalProductGroup externalProductGroup,
        ExternalProductPolicy externalProductPolicy,
        List<ExternalProductProcessingResult> externalProductProcessingResults
) {
    public long getProductGroupId(){
        return externalProductGroup.productGroupId();
    }
}
