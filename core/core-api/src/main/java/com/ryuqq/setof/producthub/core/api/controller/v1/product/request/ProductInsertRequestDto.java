package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.OptionCommand;
import com.ryuqq.setof.domain.core.product.ProductCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProductInsertRequestDto(
        boolean soldOutYn,
        boolean displayYn,

        @NotNull(message = "Quantity cannot be null.")
        @Max(value = 999, message = "Quantity must be 999 or less.")
        int quantity,

        @NotNull(message = "Additional Price cannot be null.")
        @DecimalMax(value = "100000000", message = "Additional Price must be less than or equal to 100,000,000.")
        BigDecimal additionalPrice,

        @Valid
        List<ProductOptionInsertRequestDto> options
) {

    public ProductCommand toProductCommand() {
        List<OptionCommand> optionCommands = options.stream()
                .map(ProductOptionInsertRequestDto::toOption)
                .toList();

        return new ProductCommand(soldOutYn, displayYn, quantity, additionalPrice, optionCommands);
    }





}
