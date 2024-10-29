package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.category.Category;

import java.util.List;

public class ProductGroup{
    private long productGroupId;
    private long sellerId;
    private List<Category> categories;
    private Brand brand;
    private String productGroupName;
    private String styleCode;
    private ProductCondition productCondition;
    private ManagementType managementType;
    private OptionType optionType;
    private Price price;
    private boolean soldOutYn;
    private boolean displayYn;
    private ProductStatus productStatus;

    protected ProductGroup() {}

    public ProductGroup(long productGroupId, long sellerId, Category category, Brand brand, String productGroupName, String styleCode, ProductCondition productCondition, ManagementType managementType, OptionType optionType, Price price, boolean soldOutYn, boolean displayYn, ProductStatus productStatus) {
        this.productGroupId = productGroupId;
        this.sellerId = sellerId;
        this.categories = List.of(category);
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
    }

    public long getProductGroupId() {
        return productGroupId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Brand getBrand() {
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

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

}
