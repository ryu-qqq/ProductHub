package com.ryuqq.setof.support.external.core;

public record ExternalMallOption(
        int quantity,
        Long optionGroupId,
        Long optionDetailId,
        String optionValue,
        Long colorId,
        String colorValue
) {}
