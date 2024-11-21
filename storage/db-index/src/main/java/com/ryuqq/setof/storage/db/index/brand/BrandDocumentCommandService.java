package com.ryuqq.setof.storage.db.index.brand;


import java.util.List;

public interface BrandDocumentCommandService {

    void insert(BrandDocument brandDocument);
    void inserts(List<BrandDocument> brandDocuments);

}
