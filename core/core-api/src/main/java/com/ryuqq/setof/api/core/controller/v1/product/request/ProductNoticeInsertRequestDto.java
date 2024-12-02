package com.ryuqq.setof.api.core.controller.v1.product.request;

import com.ryuqq.setof.domain.core.product.ProductNoticeCommand;
import com.ryuqq.setof.enums.core.Origin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductNoticeInsertRequestDto(

        @NotBlank(message = "Material cannot be blank.")
        @Size(max = 200, message = "Material must be 15 characters or less.")
        String material,

        @NotBlank(message = "Color cannot be blank.")
        @Size(max = 100, message = "Color must be 15 characters or less.")
        String color,

        @NotBlank(message = "Size cannot be blank.")
        @Size(max = 500, message = "Size must be 100 characters or less.")
        String size,

        @NotBlank(message = "Maker cannot be blank.")
        @Size(max = 50, message = "Maker must be 15 characters or less.")
        String maker,

        Origin origin,

        @NotBlank(message = "Washing Method cannot be blank.")
        @Size(max = 100, message = "Washing Method must be 100 characters or less.")
        String washingMethod,

        @NotBlank(message = "Year Month cannot be blank.")
        @Size(max = 50, message = "Year Month must be 15 characters or less.")
        String yearMonth,

        @NotBlank(message = "Assurance Standard cannot be blank.")
        @Size(max = 50, message = "Assurance Standard must be 15 characters or less.")
        String assuranceStandard,

        @NotBlank(message = "AS Phone cannot be blank.")
        @Size(max = 50, message = "AS Phone must be 15 characters or less.")
        String asPhone
) {
    public ProductNoticeCommand toProductNotice() {
        return new ProductNoticeCommand(
                material, color, size, maker, origin,
                washingMethod, yearMonth, assuranceStandard, asPhone
        );
    }
}