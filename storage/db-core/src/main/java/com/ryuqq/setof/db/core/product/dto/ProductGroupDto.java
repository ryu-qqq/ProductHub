package com.ryuqq.setof.storage.db.core.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.ProductCondition;
import com.ryuqq.setof.enums.core.ProductStatus;

import java.math.BigDecimal;
import java.util.List;

public class ProductGroupDto {

    private long productGroupId;
    private Long setOfProductGroupId;
    private long sellerId;
    private List<Long> colorIds;
    private long categoryId;
    private long brandId;
    private long configId;
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

    private ProductNoticeDto productNoticeDto;
    private ProductDeliveryDto productDeliveryDto;
    private List<ProductGroupImageDto> productGroupImageDtos;
    private ProductDetailDescriptionDto productDetailDescriptionDto;

    protected ProductGroupDto() {}

    @QueryProjection
    public ProductGroupDto(long productGroupId, Long setOfProductGroupId, long sellerId, List<Long> colorIds, long categoryId, long brandId, long configId, String productGroupName, String styleCode, ProductCondition productCondition, ManagementType managementType, OptionType optionType, BigDecimal regularPrice, BigDecimal currentPrice, int discountRate, boolean soldOutYn, boolean displayYn, ProductStatus productStatus, String keywords, ProductNoticeDto productNoticeDto, ProductDeliveryDto productDeliveryDto, List<ProductGroupImageDto> productGroupImageDtos, ProductDetailDescriptionDto productDetailDescriptionDto) {
        this.productGroupId = productGroupId;
        this.setOfProductGroupId = setOfProductGroupId;
        this.sellerId = sellerId;
        this.colorIds = colorIds;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.configId = configId;
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
        this.productNoticeDto = productNoticeDto;
        this.productDeliveryDto = productDeliveryDto;
        this.productGroupImageDtos = productGroupImageDtos;
        this.productDetailDescriptionDto = productDetailDescriptionDto;
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public Long getSetOfProductGroupId() {
        return setOfProductGroupId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public List<Long> getColorIds() {
        return colorIds;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public long getBrandId() {
        return brandId;
    }

    public long getConfigId() {
        return configId;
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

    public ProductNoticeDto getProductNoticeDto() {
        return productNoticeDto;
    }

    public ProductDeliveryDto getProductDeliveryDto() {
        return productDeliveryDto;
    }

    public List<ProductGroupImageDto> getProductGroupImageDtos() {
        return productGroupImageDtos;
    }

    public ProductDetailDescriptionDto getProductDetailDescriptionDto() {
        return productDetailDescriptionDto;
    }
}
