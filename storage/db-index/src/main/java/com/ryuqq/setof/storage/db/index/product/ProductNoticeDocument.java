package com.ryuqq.setof.storage.db.index.product;

public class ProductNoticeDocument {
    private String material;
    private String color;
    private String size;
    private String maker;
    private String origin;
    private String washingMethod;
    private String yearMonth;
    private String assuranceStandard;
    private String asPhone;

    public ProductNoticeDocument(String material, String color, String size, String maker, String origin, String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {
        this.material = material;
        this.color = color;
        this.size = size;
        this.maker = maker;
        this.origin = origin;
        this.washingMethod = washingMethod;
        this.yearMonth = yearMonth;
        this.assuranceStandard = assuranceStandard;
        this.asPhone = asPhone;
    }
}

