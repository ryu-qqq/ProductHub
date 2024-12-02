package com.ryuqq.setof.support.external.core.buyma.domain;

import com.ryuqq.setof.support.external.core.ExternalMallImageContext;
import com.ryuqq.setof.support.external.core.ExternalMallImageGroupContext;

import java.util.List;

public record BuyMaImageGroupContext(
        List<BuyMaImageContext> buyMaImageContexts
) implements ExternalMallImageGroupContext {

    @Override
    public List<? extends ExternalMallImageContext> getImages() {
        return buyMaImageContexts;
    }

}
