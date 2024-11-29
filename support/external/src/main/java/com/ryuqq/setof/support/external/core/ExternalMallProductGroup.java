package com.ryuqq.setof.support.external.core;

import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.support.utils.Money;

import java.util.List;

public record ExternalMallProductGroup(
        long productGroupId,
        long setOfProductGroupId,
        String externalProductId,
        long sellerId,
        List<Long> colorIds,
        String originName,
        String productGroupName,
        String styleCode,
        OptionType optionType,
        Money regularPrice,
        Money currentPrice,
        boolean soldOutYn,
        boolean displayYn,
        String keywords,
        ExternalMallProductDelivery delivery,
        ExternalMallProductNotice notice,
        List<ExternalMallProductImage> images,
        ExternalMallProductDetailDescription detailDescription
) {}
