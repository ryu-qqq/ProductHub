package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.support.utils.Money;

import java.util.List;

public record ExternalSyncProduct(
        String optionId,
        int quantity,
        boolean soldOutYn,
        boolean displayYn,
        String option,
        List<ExternalSyncOption> options,
        Money additionalPrice
) {

    public ExternalSyncOption getOption(int index) {
        if (options == null || options.size() <= index) {
            throw new IllegalArgumentException(
                    String.format("Invalid option index %d for product with options: %s", index, options)
            );
        }
        return options.get(index);
    }

}
