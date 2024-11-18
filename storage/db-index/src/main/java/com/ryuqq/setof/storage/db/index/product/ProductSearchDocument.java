package com.ryuqq.setof.storage.db.index.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product-search")
public class ProductSearchDocument {

    @Id
    private String productGroupId;
    private String productName;
    private String brandName;
    private String category;
    private String status;
    private Double price;
}
