package com.ryuqq.setof.storage.db.core.product.notice;

import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

@Table(name = "PRODUCT_NOTICE")
@Entity
public class ProductNoticeEntity  extends BaseEntity {

    @Column(name = "PRODUCT_GROUP_ID", nullable = false)
    private long productGroupId;

    @Column(name = "MATERIAL", length = 15, nullable = false)
    private String material;

    @Column(name = "COLOR", length = 15, nullable = false)
    private String color;

    @Column(name = "SIZE", length = 100, nullable = false)
    private String size;

    @Column(name = "MAKER", length = 15, nullable = false)
    private String maker;

    @Column(name = "ORIGIN", length = 3, nullable = false)
    @Enumerated(EnumType.STRING)
    private Origin origin;

    @Column(name = "WASHING_METHOD", length = 100, nullable = false)
    private String washingMethod;

    @Column(name = "YEAR_MONTH_DAY", length = 15, nullable = false)
    private String yearMonth;

    @Column(name = "ASSURANCE_STANDARD", length = 15, nullable = false)
    private String assuranceStandard;

    @Column(name = "AS_PHONE", length = 15, nullable = false)
    private String asPhone;

    protected ProductNoticeEntity() {}

    public ProductNoticeEntity(long productGroupId, String material, String color, String size, String maker, Origin origin, String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {
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

    protected long getProductGroupId() {
        return productGroupId;
    }

    protected String getMaterial() {
        return material;
    }

    protected String getColor() {
        return color;
    }

    protected String getSize() {
        return size;
    }

    protected String getMaker() {
        return maker;
    }

    protected Origin getOrigin() {
        return origin;
    }

    protected String getWashingMethod() {
        return washingMethod;
    }

    protected String getYearMonth() {
        return yearMonth;
    }

    protected String getAssuranceStandard() {
        return assuranceStandard;
    }

    protected String getAsPhone() {
        return asPhone;
    }

}
