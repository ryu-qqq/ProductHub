package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.Origin;

import java.util.Objects;

public class ProductNotice {

    private final long productGroupId;
    private final String material;
    private final String color;
    private final String size;
    private final String maker;
    private final Origin origin;
    private final String washingMethod;
    private final String yearMonth;
    private final String assuranceStandard;
    private final String asPhone;

    public ProductNotice(long productGroupId, String material, String color, String size, String maker, Origin origin, String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {
        this.productGroupId = productGroupId;
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

    public long getProductGroupId() {
        return productGroupId;
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




    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ProductNotice that = (ProductNotice) object;
        return productGroupId == that.productGroupId &&
                Objects.equals(material, that.material) &&
                Objects.equals(color, that.color) &&
                Objects.equals(size, that.size) &&
                Objects.equals(maker, that.maker) &&
                origin == that.origin &&
                Objects.equals(washingMethod, that.washingMethod) &&
                Objects.equals(yearMonth, that.yearMonth) &&
                Objects.equals(assuranceStandard, that.assuranceStandard) &&
                Objects.equals(asPhone, that.asPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupId, material, color, size, maker, origin, washingMethod, yearMonth, assuranceStandard, asPhone);
    }

    @Override
    public String toString() {
        return "ProductNotice{" +
                "productGroupId=" + productGroupId +
                ", material='" + material + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", maker='" + maker + '\'' +
                ", origin=" + origin +
                ", washingMethod='" + washingMethod + '\'' +
                ", yearMonth='" + yearMonth + '\'' +
                ", assuranceStandard='" + assuranceStandard + '\'' +
                ", asPhone='" + asPhone + '\'' +
                '}';
    }
}
