package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.group.ProductGroupInsertRequestEntity;
import com.ryuqq.setof.support.utils.JsonUtils;

import java.util.List;

public record ProductGroupCommandContext(
        ProductGroupCommand productGroupCommand,
        ProductNoticeCommand productNoticeCommand,
        ProductDeliveryCommand productDeliveryCommand,
        List<ProductGroupImageCommand> productGroupImageCommands,
        ProductDetailDescriptionCommand productDetailDescriptionCommand,
        List<ProductCommand> productCommands
) {
    public ProductGroupInsertRequestEntity toEntity(long productGroupId) {
        return new ProductGroupInsertRequestEntity(
                productGroupId,
                JsonUtils.toJson(this)
        );
    }

}
