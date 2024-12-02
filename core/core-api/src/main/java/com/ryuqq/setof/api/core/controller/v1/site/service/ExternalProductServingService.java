package com.ryuqq.setof.api.core.controller.v1.site.service;

import com.ryuqq.setof.api.core.controller.v1.site.mapper.ExternalProductContextResponseMapper;
import com.ryuqq.setof.api.core.controller.v1.site.mapper.ExternalProductSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.site.request.ExternalProductGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.site.response.ExternalProductContextResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.site.external.ExternalProductContextFinder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductServingService {

    private final ExternalProductContextResponseMapper externalProductContextResponseMapper;
    private final ExternalProductSliceMapper externalProductSliceMapper;
    private final ExternalProductContextFinder externalProductContextFinder;

    public ExternalProductServingService(ExternalProductContextResponseMapper externalProductContextResponseMapper, ExternalProductSliceMapper externalProductSliceMapper, ExternalProductContextFinder externalProductContextFinder) {
        this.externalProductContextResponseMapper = externalProductContextResponseMapper;
        this.externalProductSliceMapper = externalProductSliceMapper;
        this.externalProductContextFinder = externalProductContextFinder;
    }


    public Slice<ExternalProductContextResponse> fetchExternalProductByFilter(ExternalProductGetRequestDto requestDto){
        long externalProductCount = externalProductContextFinder.contextByFilter(requestDto.toExternalProductFilter());

        if(externalProductCount == 0){
            return externalProductSliceMapper.toSlice(List.of(), requestDto.toExternalProductFilter().pageSize(), externalProductCount);
        }

        List<ExternalProductContextResponse> responses = externalProductContextFinder.fetchExternalProductContextsByFilter(requestDto.toExternalProductFilter()).stream()
                .map(externalProductContextResponseMapper::toResponse)
                .toList();

        return externalProductSliceMapper.toSlice(responses, requestDto.toExternalProductFilter().pageSize(), externalProductCount);

    }

}
