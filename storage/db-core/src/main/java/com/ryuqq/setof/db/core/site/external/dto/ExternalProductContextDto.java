package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.storage.db.core.product.dto.ProductProcessingResultDto;

import java.util.Set;

public class ExternalProductContextDto {

    private ExternalProductGroupDto externalProductGroupDto;
    private ExternalProductPolicyDto externalProductPolicyDto;
    private Set<ProductProcessingResultDto> productProcessingResultDto;

    @QueryProjection
    public ExternalProductContextDto(ExternalProductGroupDto externalProductGroupDto, ExternalProductPolicyDto externalProductPolicyDto, Set<ProductProcessingResultDto> productProcessingResultDto) {
        this.externalProductGroupDto = externalProductGroupDto;
        this.externalProductPolicyDto = externalProductPolicyDto;
        this.productProcessingResultDto = productProcessingResultDto;
    }

    public ExternalProductPolicyDto getExternalProductPolicyDto() {
        return externalProductPolicyDto;
    }

    public ExternalProductGroupDto getExternalProductGroupDto() {
        return externalProductGroupDto;
    }

    public Set<ProductProcessingResultDto> getExternalProductProcessingResultDto() {
        return productProcessingResultDto;
    }
}
