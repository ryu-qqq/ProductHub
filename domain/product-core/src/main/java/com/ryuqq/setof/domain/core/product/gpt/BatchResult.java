package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductDataType;

public interface BatchResult {

    long getProductGroupId();
    ProductDataType getProductDataType();
}
