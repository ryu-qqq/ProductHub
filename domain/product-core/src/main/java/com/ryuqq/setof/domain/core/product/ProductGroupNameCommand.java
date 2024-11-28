package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupNameConfigDto;

public record ProductGroupNameCommand(
        long productGroupNameConfigId,
        String productGroupName
) {

    public ProductGroupNameConfigDto toProductGroupNameConfigDto(){
        return null;
        //return new ProductGroupNameConfigDto(productGroupNameConfigId, productGroupName);
    }
}
