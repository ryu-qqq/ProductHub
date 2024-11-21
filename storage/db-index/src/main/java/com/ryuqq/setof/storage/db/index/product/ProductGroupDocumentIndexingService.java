package com.ryuqq.setof.storage.db.index.product;

import java.util.List;

public interface ProductGroupDocumentIndexingService {

    void insert(ProductGroupCommandContextDocument productGroupCommandContextDocument);
    void inserts(List<ProductGroupCommandContextDocument> productGroupCommandContextDocuments);

}
