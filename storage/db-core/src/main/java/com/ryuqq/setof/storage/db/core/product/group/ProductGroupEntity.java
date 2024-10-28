package com.ryuqq.setof.storage.db.core.product.group;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Table(name = "PRODUCT_GROUP")
@Entity
public class ProductGroupEntity extends BaseEntity {

    @Column(name = "SELLER_ID", nullable = false)
    private long sellerId;

    @Column(name = "CATEGORY_ID", nullable = false)
    private long categoryId;

    @Column(name = "BRAND_ID", nullable = false)
    private long brandId;

    @Column(name = "PRODUCT_GROUP_NAME", length = 150, nullable = false)
    private String productGroupName;

    @Column(name = "STYLE_CODE", length = 50)
    private String styleCode;

    @Column(name = "PRODUCT_CONDITION", length = 15, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductCondition productCondition;

    @Column(name = "MANAGEMENT_TYPE", length = 15, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ManagementType managementType;

    @Column(name = "OPTION_TYPE", length = 20, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OptionType optionType;

    @Column(name = "REGULAR_PRICE", nullable = false)
    private BigDecimal regularPrice;

    @Column(name = "CURRENT_PRICE", nullable = false)
    private BigDecimal currentPrice;

    @Column(name = "DISCOUNT_RATE", nullable = false)
    private int discountRate;

    @Column(name = "SOLD_OUT_YN", nullable = false)
    private boolean soldOutYn;

    @Column(name = "DISPLAY_YN",  nullable = false)
    private boolean displayYn;

    @Column(name = "PRODUCT_STATUS", length = 10,  nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ProductStatus productStatus;

    protected ProductGroupEntity() {}

    public ProductGroupEntity(long sellerId, long categoryId, long brandId, String productGroupName, String styleCode, ProductCondition productCondition, ManagementType managementType, OptionType optionType, BigDecimal regularPrice, BigDecimal currentPrice, int discountRate, boolean soldOutYn, boolean displayYn, ProductStatus productStatus) {
        this.sellerId = sellerId;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.productGroupName = productGroupName;
        this.styleCode = styleCode;
        this.productCondition = productCondition;
        this.managementType = managementType;
        this.optionType = optionType;
        this.regularPrice = regularPrice;
        this.currentPrice = currentPrice;
        this.discountRate = discountRate;
        this.soldOutYn = soldOutYn;
        this.displayYn = displayYn;
        this.productStatus = productStatus;
    }


}
