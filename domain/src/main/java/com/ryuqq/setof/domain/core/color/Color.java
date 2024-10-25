package com.ryuqq.setof.domain.core.color;

import com.ryuqq.setof.domain.core.generic.LastDomainIdProvider;

public record Color(
        long id,
        String colorName
) implements LastDomainIdProvider {
    @Override
    public Long getId() {
        return id;
    }

}
