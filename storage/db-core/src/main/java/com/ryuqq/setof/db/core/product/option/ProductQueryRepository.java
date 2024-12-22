package com.ryuqq.setof.storage.db.core.product.option;

import com.ryuqq.setof.storage.db.core.product.dto.ProductContextDto;

import java.util.List;

public interface ProductQueryRepository {
    List<ProductContextDto> fetchByProductGroupIds(List<Long> productGroupIds);
}
