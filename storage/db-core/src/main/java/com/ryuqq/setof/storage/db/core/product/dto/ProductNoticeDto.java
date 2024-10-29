package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.core.Origin;

public class ProductNoticeDto {
    private String material;
    private String color;
    private String size;
    private String maker;
    private Origin origin;
    private String washingMethod;
    private String yearMonth;
    private String assuranceStandard;
    private String asPhone;

    protected ProductNoticeDto() {}

    @QueryProjection
    public ProductNoticeDto(String material, String color, String size, String maker, Origin origin, String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {
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

    public String getMaterial() {
        return material;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getMaker() {
        return maker;
    }

    public Origin getOrigin() {
        return origin;
    }

    public String getWashingMethod() {
        return washingMethod;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public String getAssuranceStandard() {
        return assuranceStandard;
    }

    public String getAsPhone() {
        return asPhone;
    }
}
