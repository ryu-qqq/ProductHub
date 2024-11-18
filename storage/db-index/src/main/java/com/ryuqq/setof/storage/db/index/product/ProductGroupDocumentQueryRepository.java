package com.ryuqq.setof.storage.db.index.product;

import java.util.List;

public interface ProductGroupDocumentQueryRepository {

    List<ProductGroupCommandContextDocument> fetchProductGroupCommandContextDocument(List<Long> productGroupIds);

}
