package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryDto;

import java.math.BigDecimal;

public class ProductGroupDto {
    private long productGroupId;
    private long sellerId;
    private CategoryDto categoryDto;
    private BrandDto brandDto;
    private String productGroupName;
    private String styleCode;
    private ProductCondition productCondition;
    private ManagementType managementType;
    private OptionType optionType;
    private BigDecimal regularPrice;
    private BigDecimal currentPrice;
    private int discountRate;
    private boolean soldOutYn;
    private boolean displayYn;
    private ProductStatus productStatus;
    private String keywords;

    protected ProductGroupDto() {}

    @QueryProjection
    public ProductGroupDto(long productGroupId, long sellerId, CategoryDto categoryDto, BrandDto brandDto, String productGroupName, String styleCode, ProductCondition productCondition, ManagementType managementType, OptionType optionType, BigDecimal regularPrice, BigDecimal currentPrice, int discountRate, boolean soldOutYn, boolean displayYn, ProductStatus productStatus, String keywords) {
        this.productGroupId = productGroupId;
        this.sellerId = sellerId;
        this.categoryDto = categoryDto;
        this.brandDto = brandDto;
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
        this.keywords = keywords;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public BrandDto getBrandDto() {
        return brandDto;
    }

    public String getProductGroupName() {
        return productGroupName;
    }

    public String getStyleCode() {
        return styleCode;
    }

    public ProductCondition getProductCondition() {
        return productCondition;
    }

    public ManagementType getManagementType() {
        return managementType;
    }

    public OptionType getOptionType() {
        return optionType;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public int getDiscountRate() {
        return discountRate;
    }

    public boolean isSoldOutYn() {
        return soldOutYn;
    }

    public boolean isDisplayYn() {
        return displayYn;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public String getKeywords() {
        return keywords;
    }

}
