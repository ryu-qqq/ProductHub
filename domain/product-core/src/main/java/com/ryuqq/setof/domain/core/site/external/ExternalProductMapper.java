package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.Price;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class ExternalProductMapper {

    public ExternalProduct toDomain(ExternalProductDto externalProductDto){
        return new ExternalProduct(
                externalProductDto.getId(),
                externalProductDto.getSiteId(),
                externalProductDto.getSiteName(),
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
                getCategoryPaths(externalProductDto.getCategoryPath()),
                externalProductDto.getExternalBrandId(),
                externalProductDto.getExternalCategoryId(),
                externalProductDto.getInsertDate(),
                externalProductDto.getUpdateDate()
        );
    }

    public List<Long> getCategoryPaths(String path){
        return Stream.of(path.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .toList();
    }


}
