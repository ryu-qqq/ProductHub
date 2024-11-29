package com.ryuqq.setof.api.core.controller.v1.product.service;

import com.ryuqq.setof.api.core.controller.v1.product.mapper.ProductGroupContextResponseMapper;
import com.ryuqq.setof.api.core.controller.v1.product.mapper.ProductGroupProcessingDataSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.product.request.ProductGroupGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.product.response.GptTrainingDataResponse;
import com.ryuqq.setof.api.core.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.product.ProductGroupCommandService;
import com.ryuqq.setof.enums.core.ProductStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GptTrainingDataServingService {

    private final ProductGroupContextService productGroupContextService;
    private final ProductGroupCommandService productGroupCommandService;
    private final ProductGroupProcessingDataSliceMapper productGroupProcessingDataSliceMapper;

    public GptTrainingDataServingService(ProductGroupContextService productGroupContextService, ProductGroupCommandService productGroupCommandService, ProductGroupContextResponseMapper productGroupContextResponseMapper, ProductGroupProcessingDataSliceMapper productGroupProcessingDataSliceMapper) {
        this.productGroupContextService = productGroupContextService;
        this.productGroupCommandService = productGroupCommandService;
        this.productGroupProcessingDataSliceMapper = productGroupProcessingDataSliceMapper;
    }


    public Slice<GptTrainingDataResponse> getProductGroupsForGptTraining(ProductGroupGetRequestDto requestDto){
        Slice<ProductGroupContextResponse> productGroupContextResponseSlice = productGroupContextService.fetchProductGroupContextsByFilter(requestDto);
        List<Long> productGroupIds = productGroupContextResponseSlice.getContent().stream().map(p -> p.productGroup().productGroupId()).toList();
        productGroupCommandService.updateStatus(productGroupIds, ProductStatus.GATHERING);

        List<GptTrainingDataResponse> contents = productGroupContextResponseSlice.getContent()
                .stream()
                .map(p -> new GptTrainingDataResponse(
                                p.productGroup(),
                                p.products(),
                                p.categories(),
                                p.brand()
                        )
                ).toList();

        return productGroupProcessingDataSliceMapper.toSlice(contents, requestDto.pageSize(), productGroupContextResponseSlice.getTotalElements());
    }



}
