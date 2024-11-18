package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.ProductCondition;
import com.ryuqq.setof.enums.core.ProductStatus;
import com.ryuqq.setof.domain.core.brand.BrandRecord;
import com.ryuqq.setof.domain.core.category.CategoryRecord;

import java.util.List;
import java.util.Objects;

public class ProductGroup {
    private final long productGroupId;
    private final long sellerId;
    private final Color color;
    private List<CategoryRecord> categories;
    private final BrandRecord brand;
    private final String productGroupName;
    private final String styleCode;
    private final ProductCondition productCondition;
    private final ManagementType managementType;
    private final OptionType optionType;
    private final Price price;
    private final boolean soldOutYn;
    private final boolean displayYn;
    private final ProductStatus productStatus;
    private final String keywords;

    public ProductGroup(long productGroupId, long sellerId, Color color, List<CategoryRecord> categories, BrandRecord brand, String productGroupName, String styleCode, ProductCondition productCondition, ManagementType managementType, OptionType optionType, Price price, boolean soldOutYn, boolean displayYn, ProductStatus productStatus, String keywords) {
        this.productGroupId = productGroupId;
        this.sellerId = sellerId;
        this.color = color;
        this.categories = categories;
        this.brand = brand;
        this.productGroupName = productGroupName;
        this.styleCode = styleCode;
        this.productCondition = productCondition;
        this.managementType = managementType;
        this.optionType = optionType;
        this.price = price;
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

    public Color getColor() {
        return color;
    }

    public List<CategoryRecord> getCategories() {
        return categories;
    }

    public BrandRecord getBrand() {
        return brand;
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

    public Price getPrice() {
        return price;
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

    public void setCategories(List<CategoryRecord> categories) {
        this.categories = categories;
    }

    public String getKeywords() {
        return keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductGroup that = (ProductGroup) o;
        return productGroupId == that.productGroupId &&
                sellerId == that.sellerId &&
                soldOutYn == that.soldOutYn &&
                displayYn == that.displayYn &&
                Objects.equals(color, that.color) &&
                Objects.equals(categories, that.categories) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(productGroupName, that.productGroupName) &&
                Objects.equals(styleCode, that.styleCode) &&
                Objects.equals(productCondition, that.productCondition) &&
                Objects.equals(managementType, that.managementType) &&
                Objects.equals(optionType, that.optionType) &&
                Objects.equals(price, that.price) &&
                Objects.equals(productStatus, that.productStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productGroupId, sellerId, color, categories, brand, productGroupName,
                styleCode, productCondition, managementType, optionType, price,
                soldOutYn, displayYn, productStatus);
    }

    @Override
    public String toString() {
        return "ProductGroup{" +
                "productGroupId=" + productGroupId +
                ", sellerId=" + sellerId +
                ", color=" + color +
                ", categories=" + categories +
                ", brand=" + brand +
                ", productGroupName='" + productGroupName + '\'' +
                ", styleCode='" + styleCode + '\'' +
                ", productCondition=" + productCondition +
                ", managementType=" + managementType +
                ", optionType=" + optionType +
                ", price=" + price +
                ", soldOutYn=" + soldOutYn +
                ", displayYn=" + displayYn +
                ", productStatus=" + productStatus +
                '}';
    }

}









