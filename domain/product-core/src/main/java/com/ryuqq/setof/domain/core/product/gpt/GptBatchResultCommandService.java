package com.ryuqq.setof.domain.core.product.gpt;

import com.ryuqq.setof.enums.core.ProductDataType;

import java.util.List;

public interface GptBatchResultCommandService<T extends GptBatchResult> {

    void execute(T t);
    void execute(List<T> tList);
    ProductDataType getProductDataType();

}
