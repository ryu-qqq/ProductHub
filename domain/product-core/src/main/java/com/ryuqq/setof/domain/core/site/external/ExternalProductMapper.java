package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.Price;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductDto;
import org.springframework.stereotype.Component;

@Component
public class ExternalProductMapper {

    public ExternalProduct toDomain(ExternalProductDto externalProductDto){
        return new ExternalProduct(
                externalProductDto.getId(),
                externalProductDto.getSiteId(),
                externalProductDto.getProductGroupId(),
                externalProductDto.getPolicyId(),
                externalProductDto.getExternalProductId(),
                externalProductDto.getProductName(),
                new Price(externalProductDto.getRegularPrice(), externalProductDto.getCurrentPrice()),
                externalProductDto.getStatus(),
                externalProductDto.isSoldOutYn(),
                externalProductDto.isDisplayYn(),
                externalProductDto.getSellerId(),
                externalProductDto.getInternalBrandId(),
                externalProductDto.getInternalCategoryId(),
                externalProductDto.getExternalBrandId(),
                externalProductDto.getExternalCategoryId()
        );
    }

}
