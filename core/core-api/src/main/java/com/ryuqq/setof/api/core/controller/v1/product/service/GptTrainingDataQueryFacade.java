package com.ryuqq.setof.api.core.controller.v1.product.service;

import com.ryuqq.setof.api.core.controller.v1.product.mapper.ProductGroupProcessingDataSliceMapper;
import com.ryuqq.setof.api.core.controller.v1.product.request.GptTrainingDataFilterRequestDto;
import com.ryuqq.setof.api.core.controller.v1.product.response.GptTrainingDataResponse;
import com.ryuqq.setof.api.core.controller.v1.site.response.ExternalMallProductPendingDataResponse;
import com.ryuqq.setof.domain.core.generic.Slice;
import com.ryuqq.setof.domain.core.product.ProductGroupCommandService;
import com.ryuqq.setof.domain.core.product.ProductGroupContext;
import com.ryuqq.setof.domain.core.product.ProductGroupContextQueryService;
import com.ryuqq.setof.domain.core.site.external.ExternalProductCommandService;
import com.ryuqq.setof.domain.core.site.external.ExternalProductContext;
import com.ryuqq.setof.domain.core.site.external.ExternalProductQueryService;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GptTrainingDataQueryFacade {

    private final ProductGroupCommandService productGroupCommandService;
    private final ProductGroupContextQueryService productGroupContextQueryService;
    private final ProductGroupProcessingDataSliceMapper productGroupProcessingDataSliceMapper;
    private final ExternalProductCommandService externalProductCommandService;
    private final ExternalProductQueryService externalProductQueryService;

    public GptTrainingDataQueryFacade(ProductGroupCommandService productGroupCommandService, ProductGroupContextQueryService productGroupContextQueryService, ProductGroupProcessingDataSliceMapper productGroupProcessingDataSliceMapper, ExternalProductCommandService externalProductCommandService, ExternalProductQueryService externalProductQueryService) {
        this.productGroupCommandService = productGroupCommandService;
        this.productGroupContextQueryService = productGroupContextQueryService;
        this.productGroupProcessingDataSliceMapper = productGroupProcessingDataSliceMapper;
        this.externalProductCommandService = externalProductCommandService;
        this.externalProductQueryService = externalProductQueryService;
    }


    public Slice<GptTrainingDataResponse> getProductGroupsForGptTraining(GptTrainingDataFilterRequestDto filter){
        long productGroupCount = productGroupContextQueryService.countProductContextByFilter(filter.toProductGroupFilter());

        if(productGroupCount == 0){
            return productGroupProcessingDataSliceMapper.toSlice(List.of(), filter.pageSize(), 0);
        }

        List<ProductGroupContext> productGroupContexts = productGroupContextQueryService.fetchProductGroupContextsByFilter(filter.toProductGroupFilter());

        List<Long> productGroupIds = getProductGroupIds(productGroupContexts);
        Map<Long, List<ExternalProductContext>> productGroupIdMap = toProductGroupIdMap(filter, productGroupIds);

        List<GptTrainingDataResponse> results = productGroupContexts.stream().map(p -> {
            List<ExternalProductContext> datas = productGroupIdMap.getOrDefault(p.getProductGroup().productGroupId(), List.of());
            if (!datas.isEmpty()) {
                List<ExternalMallProductPendingDataResponse> list = datas.stream().map(ExternalMallProductPendingDataResponse::toResponse).toList();
                return toProductGroupProcessingDataResponse(p, list);
            } else {
                return toProductGroupProcessingDataResponse(p, List.of());
            }
        }).toList();

        productGroupCommandService.updateStatus(productGroupIds, ProductStatus.GATHERING);
        externalProductCommandService.updateStatusByProductGroupId(productGroupIds, SyncStatus.PROCESSING);


        return productGroupProcessingDataSliceMapper.toSlice(results, filter.pageSize(), productGroupCount);
    }

    private GptTrainingDataResponse toProductGroupProcessingDataResponse(ProductGroupContext productGroupContext, List<ExternalMallProductPendingDataResponse> productGroupProcessingDataResponses){
        return null;
//        return new GptTrainingDataResponse(
//                ProductGroupContextResponse.toProductGroupResponse(
//                        productGroupContext.getProductGroup()),
//
//                    productGroupContext.getProducts().stream()
//                            .map(ProductGroupContextResponse::toProductResponse)
//                        .collect(Collectors.toSet()),
//
//                    ProductGroupContextResponse.toProductNoticeResponse(productGroupContext.getProductGroup().notice()),
//
//                    productGroupProcessingDataResponses
//        );

    }


    private List<Long> getProductGroupIds(List<ProductGroupContext> productGroupContexts){
        return productGroupContexts.stream()
                .map(p -> p.getProductGroup().productGroupId())
                .toList();
    }


    private Map<Long, List<ExternalProductContext>> toProductGroupIdMap(GptTrainingDataFilterRequestDto filter, List<Long> productGroupIds){
        return externalProductQueryService.findExternalProductContext(filter.toExternalFilter(productGroupIds))
                .stream()
                .collect(Collectors.groupingBy(ExternalProductContext::getProductGroupId));
    }


}
