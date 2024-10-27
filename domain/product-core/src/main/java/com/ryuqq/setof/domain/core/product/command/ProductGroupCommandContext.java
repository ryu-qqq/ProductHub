package com.ryuqq.setof.domain.core.product.command;

import java.util.List;

public record ProductGroupCommandContext(
        ProductGroupCommand productGroupCommand,
        ProductNoticeCommand productNoticeCommand,
        ProductDeliveryCommand productDeliveryCommand,
        List<ProductGroupImageCommand> productGroupImageCommands,
        ProductDetailDescriptionCommand productDetailDescriptionCommand,
        List<ProductCommand> productCommands
) {}
