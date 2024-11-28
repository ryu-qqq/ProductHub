package com.ryuqq.setof.api.core.controller.v1.product.response;

import com.ryuqq.setof.api.core.controller.v1.site.response.ExternalMallProductPendingDataResponse;

import java.util.List;
import java.util.Set;

public record GptTrainingDataResponse(
        ProductGroupResponse productGroup,
        Set<ProductResponse> products,
        ProductNoticeResponse productNotice,
        List<ExternalMallProductPendingDataResponse> externalMallProductPendingData
) {}
