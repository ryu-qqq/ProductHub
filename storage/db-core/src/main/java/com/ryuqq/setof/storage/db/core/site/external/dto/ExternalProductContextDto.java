package com.ryuqq.setof.storage.db.core.site.external.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.util.Set;

public class ExternalProductContextDto {

    private ExternalProductDto externalProductDto;
    private ExternalProductPolicyDto externalProductPolicyDto;
    private Set<ExternalProductProcessingResultDto> externalProductProcessingResultDto;

    @QueryProjection
    public ExternalProductContextDto(ExternalProductDto externalProductDto, ExternalProductPolicyDto externalProductPolicyDto, Set<ExternalProductProcessingResultDto> externalProductProcessingResultDto) {
        this.externalProductDto = externalProductDto;
        this.externalProductPolicyDto = externalProductPolicyDto;
        this.externalProductProcessingResultDto = externalProductProcessingResultDto;
    }

    public ExternalProductPolicyDto getExternalProductPolicyDto() {
        return externalProductPolicyDto;
    }

    public ExternalProductDto getExternalProductGroupDto() {
        return externalProductDto;
    }

    public Set<ExternalProductProcessingResultDto> getExternalProductProcessingResultDto() {
        return externalProductProcessingResultDto;
    }
}
