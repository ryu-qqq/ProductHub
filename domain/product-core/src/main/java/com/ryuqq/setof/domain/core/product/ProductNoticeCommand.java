package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.db.core.product.notice.ProductNoticeEntity;

public record ProductNoticeCommand(
        String material,
        String color,
        String size,
        String maker,
        Origin origin,
        String washingMethod,
        String yearMonth,
        String assuranceStandard,
        String asPhone
) {

    public ProductNoticeEntity toEntity(long productGroupId){
        return new ProductNoticeEntity(productGroupId, material, color, size, maker,
                origin, washingMethod, yearMonth, assuranceStandard, asPhone);
    }

}
