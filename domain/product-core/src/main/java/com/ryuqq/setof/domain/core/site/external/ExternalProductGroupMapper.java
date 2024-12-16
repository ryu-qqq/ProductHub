package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.product.Price;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductGroupDto;
import com.ryuqq.setof.storage.db.core.site.external.dto.ExternalProductImageDto;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Component
public class ExternalProductGroupMapper {

    public ExternalProductGroup toDomain(ExternalProductGroupDto externalProductGroupDto){
        return new ExternalProductGroup(
                externalProductGroupDto.getId(),
                externalProductGroupDto.getSiteId(),
                externalProductGroupDto.getSiteName(),
                externalProductGroupDto.getProductGroupId(),
                externalProductGroupDto.getPolicyId(),
                externalProductGroupDto.getExternalProductGroupId(),
                externalProductGroupDto.getProductName(),
                externalProductGroupDto.getOriginName(),
                new Price(externalProductGroupDto.getRegularPrice(), externalProductGroupDto.getCurrentPrice()),
                externalProductGroupDto.getStatus(),
                externalProductGroupDto.isSoldOutYn(),
                externalProductGroupDto.isDisplayYn(),
                externalProductGroupDto.getSellerId(),
                externalProductGroupDto.getInternalBrandId(),
                externalProductGroupDto.getInternalCategoryId(),
                getCategoryPaths(externalProductGroupDto.getCategoryPath()),
                externalProductGroupDto.getExternalBrandId(),
                externalProductGroupDto.getExternalCategoryId(),
                externalProductGroupDto.getExternalExtraCategoryId(),
                externalProductGroupDto.getInsertDate(),
                externalProductGroupDto.getUpdateDate(),
                toExternalProductImages(externalProductGroupDto.getExternalProductImages())
        );
    }

    private List<Long> getCategoryPaths(String path){
        return Stream.of(path.split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .toList();
    }


    private List<ExternalProductImage> toExternalProductImages(List<ExternalProductImageDto> externalProductImageDtos){
        return externalProductImageDtos.stream()
                .sorted(Comparator.comparingInt(ExternalProductImageDto::getDisplayOrder))
                .map(e -> new ExternalProductImage(
                                e.getProductGroupId(),
                                e.getExternalProductGroupId(),
                                e.getDisplayOrder(),
                                e.getImageUrl(),
                                e.getOriginUrl()
                ))
                .toList();
    }



}
