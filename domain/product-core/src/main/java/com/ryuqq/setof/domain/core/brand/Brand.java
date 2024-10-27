package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.domain.core.generic.LastDomainIdProvider;

public record Brand(
        long id,
        String brandName,
        String brandIconImageUrl,
        boolean displayYn
) implements LastDomainIdProvider {

    @Override
    public Long getId() {
        return id;
    }

}
