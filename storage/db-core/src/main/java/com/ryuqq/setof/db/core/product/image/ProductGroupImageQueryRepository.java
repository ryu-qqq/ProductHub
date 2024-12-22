package com.ryuqq.setof.storage.db.core.product.image;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupImageDto;

import java.util.List;

public interface ProductGroupImageQueryRepository {

    List<ProductGroupImageDto> fetchByProductGroupId(long productGroupId);
}
