package com.ryuqq.setof.storage.db.index.brand;


import java.util.List;

@Deprecated
public interface BrandDocumentIndexingRepository {

    void insertBrandDocument(BrandDocument brandDocument);
    void insertAllBrandDocument(List<BrandDocument> brandDocuments);

}
