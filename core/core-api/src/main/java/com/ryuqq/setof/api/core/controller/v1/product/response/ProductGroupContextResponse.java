package com.ryuqq.setof.api.core.controller.v1.product.response;

import com.ryuqq.setof.api.core.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.api.core.controller.v1.category.response.CategoryResponse;

import java.util.List;

public record ProductGroupContextResponse(
        ProductGroupResponse productGroup,
        List<ProductResponse> products,
        List<CategoryResponse> categories,
        BrandResponse brand,
        ProductGroupConfigContextResponse config
) {}
