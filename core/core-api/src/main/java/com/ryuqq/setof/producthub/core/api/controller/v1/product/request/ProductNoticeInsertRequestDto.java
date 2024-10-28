package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.Origin;
import com.ryuqq.setof.domain.core.product.command.ProductNoticeCommand;

import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.validateString;

public record ProductNoticeInsertRequestDto(
        String material,
        String color,
        String size,
        String maker,
        Origin origin,
        String washingMethod,
        String yearMonth,
        String assuranceStandard,
        String asPhone
) {
    public ProductNoticeInsertRequestDto {
        validateFields(material, color, size, maker, washingMethod, yearMonth, assuranceStandard, asPhone);
    }

    private static void validateFields(String material, String color, String size, String maker,
                                       String washingMethod, String yearMonth, String assuranceStandard, String asPhone) {
        validateString(material, 15, "Material");
        validateString(color, 15, "Color");
        validateString(size, 100, "Size");
        validateString(maker, 15, "Maker");
        validateString(washingMethod, 100, "Washing method");
        validateString(yearMonth, 15, "Year Month");
        validateString(assuranceStandard, 15, "Assurance standard");
        validateString(asPhone, 15, "AS phone number");
    }

    public ProductNoticeCommand toProductNotice() {
        return new ProductNoticeCommand(material, color, size, maker, origin, washingMethod, yearMonth, assuranceStandard, asPhone);
    }

}
