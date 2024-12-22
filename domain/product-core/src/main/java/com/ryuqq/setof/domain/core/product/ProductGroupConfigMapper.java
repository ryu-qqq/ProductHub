package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.dto.ProductGroupConfigDto;
import org.springframework.stereotype.Component;

@Component
public class ProductGroupConfigMapper {

    public ProductGroupConfig toDomain(ProductGroupConfigDto dto) {
        return new ProductGroupConfig(
                dto.getConfigId(),
                dto.getSellerId(),
                dto.getProductGroupId(),
                dto.isActiveYn()
        );
    }
}
