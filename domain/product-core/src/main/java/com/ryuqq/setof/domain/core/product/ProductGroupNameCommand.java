package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.dto.ProductGroupNameConfigDto;

public record ProductGroupNameCommand(
        long productGroupNameConfigId,
        String productGroupName
) {

    public ProductGroupNameConfigDto toProductGroupNameConfigDto(){
        return new ProductGroupNameConfigDto(productGroupNameConfigId, productGroupName);
    }
}
