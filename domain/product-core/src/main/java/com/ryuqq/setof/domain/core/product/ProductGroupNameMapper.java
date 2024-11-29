package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupNameConfigDto;
import org.springframework.stereotype.Component;

@Component
public class ProductGroupNameMapper {

    public ProductGroupNameConfig toDomain(ProductGroupNameConfigDto dto){
        return new ProductGroupNameConfig(
                dto.getProductGroupNameConfigId(),
                dto.getConfigId(),
                dto.getProductGroupId(),
                dto.getCountryCode(),
                dto.getCustomName()
        );
    }
}
