package com.ryuqq.setof.api.core.controller.v1.site.service;

import com.ryuqq.setof.api.core.controller.v1.site.mapper.ExternalProductGroupContextResponseMapper;
import com.ryuqq.setof.api.core.controller.v1.site.mapper.ExternalProductGroupSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.site.request.ExternalProductGroupGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.response.ExternalProductGroupContextResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.site.external.ExternalProductGroupContextFinder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductServingService {

    private final ExternalProductGroupContextResponseMapper externalProductGroupContextResponseMapper;
    private final ExternalProductGroupSliceMapper externalProductGroupSliceMapper;
    private final ExternalProductGroupContextFinder externalProductGroupContextFinder;
    private final ExternalProductGroupFilterProcessor externalProductGroupFilterProcessor;

    public ExternalProductServingService(ExternalProductGroupContextResponseMapper externalProductGroupContextResponseMapper, ExternalProductGroupSliceMapper externalProductGroupSliceMapper, ExternalProductGroupContextFinder externalProductGroupContextFinder, ExternalProductGroupFilterProcessor externalProductGroupFilterProcessor) {
        this.externalProductGroupContextResponseMapper = externalProductGroupContextResponseMapper;
        this.externalProductGroupSliceMapper = externalProductGroupSliceMapper;
        this.externalProductGroupContextFinder = externalProductGroupContextFinder;
        this.externalProductGroupFilterProcessor = externalProductGroupFilterProcessor;
    }


    public Slice<ExternalProductGroupContextResponse> fetchExternalProductGroupByFilter(ExternalProductGroupGetRequestDto requestDto){
        ExternalProductGroupFilterRequest filterRequest = new ExternalProductGroupFilterRequest(requestDto);
        filterRequest = externalProductGroupFilterProcessor.process(filterRequest);

        ExternalProductGroupGetRequestDto processedDto = filterRequest.toDto();

        long externalProductGroupCount = externalProductGroupContextFinder.contextByFilter(processedDto.toExternalProductFilter());

        if(externalProductGroupCount == 0){
            return externalProductGroupSliceMapper.toSlice(List.of(), processedDto.toExternalProductFilter().pageSize(), externalProductGroupCount);
        }

        List<ExternalProductGroupContextResponse> responses = externalProductGroupContextFinder.fetchExternalProductContextsByFilter(processedDto.toExternalProductFilter()).stream()
                .map(externalProductGroupContextResponseMapper::toResponse)
                .toList();

        return externalProductGroupSliceMapper.toSlice(responses, processedDto.toExternalProductFilter().pageSize(), externalProductGroupCount);
    }

}
