package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.domain.core.product.command.ProductGroupCommand;

import java.math.BigDecimal;

import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.*;

public record ProductGroupInsertRequestDto(
        long brandId,
        long categoryId,
        long sellerId,
        String productGroupName,
        String styleCode,
        ProductCondition productCondition,
        ManagementType managementType,
        OptionType optionType,
        BigDecimal regularPrice,
        BigDecimal currentPrice,
        boolean soldOutYn,
        boolean displayYn
) {
    public ProductGroupInsertRequestDto {
        validateFields(productGroupName, styleCode, productCondition, managementType, optionType, regularPrice, currentPrice);
    }

    private void validateFields(
                                String productGroupName,
                                String styleCode,
                                ProductCondition productCondition,
                                ManagementType managementType,
                                OptionType optionType,
                                BigDecimal regularPrice,
                                BigDecimal currentPrice) {

        validateString(productGroupName, 100, "Product Group Name");
        validateString(styleCode, 50, "Style Code");
        validateBigDecimal(regularPrice, BigDecimal.valueOf(100000000), "Regular Price");
        validateBigDecimal(currentPrice, BigDecimal.valueOf(100000000), "Current Price");
        validatePrice(regularPrice, currentPrice);
        validateEnum(productCondition, "Product Condition Type");
        validateEnum(managementType, "Management Type");
        validateEnum(optionType, "Option Type");
    }

    private void validatePrice(BigDecimal regularPrice, BigDecimal currentPrice) {
        if(regularPrice.compareTo(currentPrice) < 0) {
            throw new IllegalArgumentException("Regular Price must be greater than Current Price");
        }
    }

    public ProductGroupCommand toProductGroupCommand() {
        return new ProductGroupCommand(brandId, categoryId, sellerId, productGroupName,
                styleCode, productCondition, managementType, optionType, regularPrice, currentPrice,
                soldOutYn, displayYn, ProductStatus.WAITING);
    }


}