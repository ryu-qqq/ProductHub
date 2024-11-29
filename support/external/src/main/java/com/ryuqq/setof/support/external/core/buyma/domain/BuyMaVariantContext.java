package com.ryuqq.setof.support.external.core.buyma.domain;

import java.util.List;

public record BuyMaVariantContext(
    List<BuyMaVariant> buyMaVariants,
    List<BuyMaOption> buyMaOptions,
    String optionComment,
    boolean noOptionProduct
) {
}
