package com.ryuqq.setof.api.core.controller.v1.product.service;

import com.ryuqq.setof.api.core.controller.v1.category.service.CategoryRelationProcessor;
import com.ryuqq.setof.api.core.controller.v1.product.mapper.ProductGroupContextResponseMapper;
import com.ryuqq.setof.api.core.controller.v1.product.mapper.ProductGroupContextSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.product.request.ProductGroupGetRequestDto;
import com.ryuqq.setof.api.core.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.product.ProductGroupContextQueryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupContextServingService {

    private final ProductGroupContextQueryService productGroupContextQueryService;
    private final ProductGroupContextResponseMapper productGroupContextResponseMapper;
    private final ProductGroupContextSliceMapper productGroupContextSliceMapper;
    private final ProductGroupFilterProcessor productGroupFilterProcessor;

    public ProductGroupContextServingService(ProductGroupContextQueryService productGroupContextQueryService, ProductGroupContextResponseMapper productGroupContextResponseMapper, ProductGroupContextSliceMapper productGroupContextSliceMapper, ProductGroupFilterProcessor productGroupFilterProcessor) {
        this.productGroupContextQueryService = productGroupContextQueryService;
        this.productGroupContextResponseMapper = productGroupContextResponseMapper;
        this.productGroupContextSliceMapper = productGroupContextSliceMapper;
        this.productGroupFilterProcessor = productGroupFilterProcessor;
    }


    public Slice<ProductGroupContextResponse> fetchProductGroupContextsByFilter(ProductGroupGetRequestDto requestDto) {
        ProductGroupFilterRequest filterRequest = new ProductGroupFilterRequest(requestDto);
        filterRequest = productGroupFilterProcessor.process(filterRequest);

        ProductGroupGetRequestDto processedDto = filterRequest.toDto();

        long productGroupCount = productGroupContextQueryService.countProductContextByFilter(processedDto.toProductGroupFilter());
        if (productGroupCount == 0) {
            return productGroupContextSliceMapper.toSlice(List.of(), processedDto.toProductGroupFilter().pageSize(), productGroupCount);
        }

        List<ProductGroupContextResponse> responses = productGroupContextQueryService.fetchProductGroupContextsByFilter(processedDto.toProductGroupFilter()).stream()
                .map(productGroupContextResponseMapper::toResponse)
                .toList();

        return productGroupContextSliceMapper.toSlice(responses, processedDto.toProductGroupFilter().pageSize(), productGroupCount);
    }



}
