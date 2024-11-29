package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.storage.db.core.product.dto.ProductProcessingResultDto;

import java.util.Set;

public class ExternalProductContextDto {

    private ExternalProductDto externalProductDto;
    private ExternalProductPolicyDto externalProductPolicyDto;
    private Set<ProductProcessingResultDto> productProcessingResultDto;

    @QueryProjection
    public ExternalProductContextDto(ExternalProductDto externalProductDto, ExternalProductPolicyDto externalProductPolicyDto, Set<ProductProcessingResultDto> productProcessingResultDto) {
        this.externalProductDto = externalProductDto;
        this.externalProductPolicyDto = externalProductPolicyDto;
        this.productProcessingResultDto = productProcessingResultDto;
    }

    public ExternalProductPolicyDto getExternalProductPolicyDto() {
        return externalProductPolicyDto;
    }

    public ExternalProductDto getExternalProductGroupDto() {
        return externalProductDto;
    }

    public Set<ProductProcessingResultDto> getExternalProductProcessingResultDto() {
        return productProcessingResultDto;
    }
}
