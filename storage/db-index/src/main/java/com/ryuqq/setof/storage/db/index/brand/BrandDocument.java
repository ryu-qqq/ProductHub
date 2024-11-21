package com.ryuqq.setof.storage.db.index.brand;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;

@Document(indexName = "brand-document", writeTypeHint = WriteTypeHint.FALSE)
public class BrandDocument {

    @Id
    private String brandId;
    private String brandName;
    private String brandNameKr;
    private String brandIconImageUrl;
    private Boolean displayYn;

    protected BrandDocument(){}

    public BrandDocument(String brandId,  String brandName, String brandNameKr, String brandIconImageUrl, Boolean displayYn) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.brandNameKr = brandNameKr;
        this.brandIconImageUrl = brandIconImageUrl;
        this.displayYn = displayYn;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getBrandNameKr() {
        return brandNameKr;
    }

    public String getBrandIconImageUrl() {
        return brandIconImageUrl;
    }

    public Boolean getDisplayYn() {
        return displayYn;
    }
}
