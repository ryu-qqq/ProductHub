package com.ryuqq.setof.support.external.core.buyma.domain;

import com.ryuqq.setof.support.external.core.ExternalMallOptionContext;

import java.util.List;

public record BuyMaOptionContext(
        List<BuyMaOption> buyMaOptions,
        List<BuyMaVariant> buyMaVariants,
        String optionComment
) implements ExternalMallOptionContext {

    @Override
    public BuyMaCustomOptions getCustomAttributes() {
        return new BuyMaCustomOptions(buyMaOptions, buyMaVariants, optionComment);
    }

    public record BuyMaCustomOptions(List<BuyMaOption> options, List<BuyMaVariant> variants, String optionComment) {}

}
