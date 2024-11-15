package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.domain.core.product.ProductGroupCommand;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductGroupInsertRequestDto(

        @NotNull(message = "Brand ID cannot be null.")
        @Positive(message = "Brand ID must be a positive number.")
        long brandId,

        @NotNull(message = "Category ID cannot be null.")
        @Positive(message = "Category ID must be a positive number.")
        long categoryId,

        @NotNull(message = "Seller ID cannot be null.")
        @Positive(message = "Seller ID must be a positive number.")
        long sellerId,

        @NotBlank(message = "Product Group Name cannot be blank.")
        @Size(max = 100, message = "Product Group Name must be 100 characters or less.")
        String productGroupName,

        @NotBlank(message = "Style Code cannot be blank.")
        @Size(max = 50, message = "Style Code must be 50 characters or less.")
        String styleCode,

        @NotNull(message = "Product Condition Type cannot be null.")
        ProductCondition productCondition,

        @NotNull(message = "Management Type cannot be null.")
        ManagementType managementType,

        @NotNull(message = "Option Type cannot be null.")
        OptionType optionType,

        @NotNull(message = "Regular Price cannot be null.")
        @DecimalMax(value = "100000000", inclusive = true, message = "Regular Price must be less than or equal to 100,000,000.")
        BigDecimal regularPrice,

        @NotNull(message = "Current Price cannot be null.")
        @DecimalMax(value = "100000000", inclusive = true, message = "Current Price must be less than or equal to 100,000,000.")
        BigDecimal currentPrice,

        boolean soldOutYn,
        boolean displayYn,
        String keywords
) {
    public ProductGroupCommand toProductGroupCommand() {
        return new ProductGroupCommand(brandId, categoryId, sellerId, productGroupName,
                styleCode, productCondition, managementType, optionType, regularPrice, currentPrice,
                soldOutYn, displayYn, ProductStatus.WAITING, keywords);
    }

}