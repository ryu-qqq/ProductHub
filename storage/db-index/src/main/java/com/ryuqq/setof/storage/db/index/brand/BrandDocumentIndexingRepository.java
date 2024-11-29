package com.ryuqq.setof.storage.db.index.brand;


import java.util.List;

public interface BrandDocumentIndexingRepository {

    void insertBrandDocument(BrandDocument brandDocument);
    void insertAllBrandDocument(List<BrandDocument> brandDocuments);

}
