package com.ryuqq.setof.storage.db.index.product;

import java.util.List;

@Deprecated
public interface ProductGroupDocumentQueryRepository {

    List<ProductGroupCommandContextDocument> fetchByIds(List<Long> productGroupIds);

}
