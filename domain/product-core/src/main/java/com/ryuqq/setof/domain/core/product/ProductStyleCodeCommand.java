package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.dto.ProductStyleCodeDto;

public record ProductStyleCodeCommand(
        long productGroupId,
        String styleCode
)
{

    public ProductStyleCodeDto toProductStyleCodeDto() {
        return new ProductStyleCodeDto(productGroupId, styleCode);
    }

}
