package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.ManagementType;

public class ProductGroupGetRequestDto {
    private ManagementType managementType;
    private Long categoryId;
    private Long brandId;
    private Long colorId;
    private Long cursorId;
    private int pageSize;
    private boolean soldOutYn;
    private boolean displayYn;
    private Long minSalePrice;
    private Long maxSalePrice;
    private Long minDiscountRate;
    private Long maxDiscountRate;
}
