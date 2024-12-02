package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.support.utils.Money;

import java.util.List;

public record ExternalSyncProduct(
        int quantity,
        boolean soldOutYn,
        boolean displayYn,
        String option,
        List<ExternalSyncOption> options,
        Money additionalPrice
) {



}