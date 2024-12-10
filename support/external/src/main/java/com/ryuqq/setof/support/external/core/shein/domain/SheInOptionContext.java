package com.ryuqq.setof.support.external.core.shein.domain;

import java.util.List;

public record SheInOptionContext(
        List<SheInSku> skus
) implements ExternalMallOptionContext {

    @Override
    public SheInCustomOptions getCustomAttributes() {
        return new SheInCustomOptions(skus);
    }

    public record SheInCustomOptions(List<SheInSku> skus) {}

}
