package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductGroupUpdater {

    private final ProductGroup productGroup;

    public ProductGroupUpdater(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    private boolean isUpdateBrand(long newBrandId) {
        return productGroup.getBrand().id() != newBrandId;
    }

    private boolean isUpdateCategory(long newCategoryId) {
        return productGroup.getCategories().stream().noneMatch(c -> c.id() == newCategoryId);
    }

    public boolean isUpdateProductName(String newName) {
        return !Objects.equals(productGroup.getProductGroupName(), newName);
    }

    public boolean isUpdateStyleCode(String newStyleCode) {
        return !Objects.equals(productGroup.getStyleCode(), newStyleCode);
    }

    public boolean isUpdateProductCondition(ProductCondition newCondition) {
        return productGroup.getProductCondition() != newCondition;
    }

    public boolean isUpdateManagementType(ManagementType newManagementType) {
        return productGroup.getManagementType() != newManagementType;
    }

    public boolean isUpdateOptionType(OptionType newOptionType) {
        return productGroup.getOptionType() != newOptionType;
    }

    public boolean isUpdatePrice(BigDecimal newRegularPrice, BigDecimal newCurrentPrice) {
        return !Objects.equals(productGroup.getPrice().getRegularPrice(), newRegularPrice) ||
                !Objects.equals(productGroup.getPrice().getCurrentPrice(), newCurrentPrice);
    }

    public boolean isUpdateSoldOutStatus(boolean newSoldOutYn) {
        return productGroup.isSoldOutYn() != newSoldOutYn;
    }

    public boolean isUpdateDisplayStatus(boolean newDisplayYn) {
        return productGroup.isDisplayYn() != newDisplayYn;
    }

    public boolean isUpdateProductStatus(ProductStatus newStatus) {
        return productGroup.getProductStatus() != newStatus;
    }


    public boolean hasUpdates(ProductGroupCommand command) {
        return isUpdateBrand(command.brandId()) ||
                isUpdateCategory(command.categoryId()) ||
                isUpdateProductName(command.productGroupName()) ||
                isUpdateStyleCode(command.styleCode()) ||
                isUpdateProductCondition(command.productCondition()) ||
                isUpdateManagementType(command.managementType()) ||
                isUpdateOptionType(command.optionType()) ||
                isUpdatePrice(command.regularPrice(), command.currentPrice()) ||
                isUpdateSoldOutStatus(command.soldOutYn()) ||
                isUpdateDisplayStatus(command.displayYn()) ||
                isUpdateProductStatus(command.productStatus());
    }


}
