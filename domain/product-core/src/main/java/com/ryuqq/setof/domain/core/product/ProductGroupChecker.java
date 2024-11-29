package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.support.utils.Money;
import com.ryuqq.setof.enums.core.ManagementType;
import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.ProductCondition;
import com.ryuqq.setof.enums.core.ProductStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class ProductGroupChecker implements UpdateChecker<ProductGroup, ProductGroupCommand>{

    @Override
    public UpdateDecision checkUpdates(ProductGroup existing, ProductGroupCommand updated) {
        UpdateDecision decision = new UpdateDecision();

        boolean anyChangeDetected = isUpdateBrand(existing, updated.brandId()) ||
                isUpdateCategory(existing, updated.categoryId()) ||
                isUpdateProductName(existing, updated.productGroupName()) ||
                isUpdateStyleCode(existing, updated.styleCode()) ||
                isUpdateProductCondition(existing, updated.productCondition()) ||
                isUpdateManagementType(existing, updated.managementType()) ||
                isUpdateOptionType(existing, updated.optionType()) ||
                isUpdateSoldOutStatus(existing, updated.soldOutYn()) ||
                isUpdateDisplayStatus(existing, updated.displayYn()) ||
                isUpdateProductStatus(existing, updated.productStatus());

        boolean priceChanged = isUpdatePrice(existing, updated.regularPrice(), updated.currentPrice());

        if (anyChangeDetected || priceChanged) {
            decision.addBatchUpdate(updated);
        }

        if (priceChanged) {
            decision.addRealTimeUpdate(updated);
        }

        return decision;
    }

    private boolean isUpdateBrand(ProductGroup existing, long newBrandId) {
        return existing.brandId() != newBrandId;
    }

    private boolean isUpdateCategory(ProductGroup existing, long newCategoryId) {
        return existing.colorIds().stream().noneMatch(c -> c == newCategoryId);
    }

    private boolean isUpdateProductName(ProductGroup existing, String newName) {
        return !Objects.equals(existing.productGroupName(), newName);
    }

    private boolean isUpdateStyleCode(ProductGroup existing, String newStyleCode) {
        return !Objects.equals(existing.styleCode(), newStyleCode);
    }

    private boolean isUpdateProductCondition(ProductGroup existing, ProductCondition newCondition) {
        return existing.productCondition() != newCondition;
    }

    private boolean isUpdateManagementType(ProductGroup existing, ManagementType newManagementType) {
        return existing.managementType() != newManagementType;
    }

    private boolean isUpdateOptionType(ProductGroup existing, OptionType newOptionType) {
        return existing.optionType() != newOptionType;
    }

    private boolean isUpdatePrice(ProductGroup existing, BigDecimal newRegularPrice, BigDecimal newCurrentPrice) {
        return !Objects.equals(existing.price().getRegularPrice(), Money.wons(newRegularPrice)) ||
                !Objects.equals(existing.price().getCurrentPrice(), Money.wons(newCurrentPrice));
    }

    private boolean isUpdateSoldOutStatus(ProductGroup existing, boolean newSoldOutYn) {
        return existing.soldOutYn() != newSoldOutYn;
    }

    private boolean isUpdateDisplayStatus(ProductGroup existing, boolean newDisplayYn) {
        return existing.displayYn() != newDisplayYn;
    }

    private boolean isUpdateProductStatus(ProductGroup existing, ProductStatus newStatus) {
        return existing.productStatus() != newStatus;
    }


}
