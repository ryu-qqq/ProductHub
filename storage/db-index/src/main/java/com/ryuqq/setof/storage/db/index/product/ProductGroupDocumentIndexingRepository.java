package com.ryuqq.setof.storage.db.index.product;

import java.util.List;

@Deprecated
public interface ProductGroupDocumentIndexingRepository {

    void insertProductGroupCommandContext(ProductGroupCommandContextDocument productGroupCommandContextDocument);
    void insertAllProductGroupCommandContext(List<ProductGroupCommandContextDocument> productGroupCommandContextDocuments);

}
