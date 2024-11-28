package com.ryuqq.setof.api.core.controller.v1.product.service;

import com.ryuqq.setof.api.core.controller.v1.product.mapper.ProductGroupContextResponseMapper;
import com.ryuqq.setof.api.core.controller.v1.product.mapper.ProductGroupContextSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.product.response.ProductGroupContextResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.product.ProductGroupContextQueryService;
import com.ryuqq.setof.domain.core.product.ProductGroupFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupContextService {

    private final ProductGroupContextQueryService productGroupContextQueryService;
    private final ProductGroupContextResponseMapper productGroupContextResponseMapper;
    private final ProductGroupContextSliceMapper productGroupContextSliceMapper;

    public ProductGroupContextService(ProductGroupContextQueryService productGroupContextQueryService, ProductGroupContextResponseMapper productGroupContextResponseMapper, ProductGroupContextSliceMapper productGroupContextSliceMapper) {
        this.productGroupContextQueryService = productGroupContextQueryService;
        this.productGroupContextResponseMapper = productGroupContextResponseMapper;
        this.productGroupContextSliceMapper = productGroupContextSliceMapper;
    }

    public Slice<ProductGroupContextResponse> fetchProductGroupContextsByFilter(ProductGroupFilter productGroupFilter){

        long productGroupCount = productGroupContextQueryService.countProductContextByFilter(productGroupFilter);

        if(productGroupCount == 0){
            return productGroupContextSliceMapper.toSlice(List.of(), productGroupFilter.pageSize(), productGroupCount);
        }

        List<ProductGroupContextResponse> responses = productGroupContextQueryService.fetchProductGroupContextsByFilter(productGroupFilter).stream()
                .map(productGroupContextResponseMapper::toResponse)
                .toList();

        return productGroupContextSliceMapper.toSlice(responses, productGroupFilter.pageSize(), productGroupCount);
    }

}
