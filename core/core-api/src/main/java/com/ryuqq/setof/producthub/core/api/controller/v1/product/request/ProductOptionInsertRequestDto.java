package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.OptionName;
import com.ryuqq.setof.domain.core.product.command.OptionCommand;

import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.validateEnum;
import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.validateString;


public record ProductOptionInsertRequestDto(
        OptionName optionName,
        String optionValue) {

    public ProductOptionInsertRequestDto {
        validateFields(optionName, optionValue);
    }

    private void validateFields(OptionName optionName,
                                String optionValue) {
        validateEnum(optionName, "Option Name");
        validateString(optionValue, 50, "Option Value");
    }

    public OptionCommand toOption() {
        return new OptionCommand(optionName, optionValue);
    }

}
