package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.OptionName;
import com.ryuqq.setof.domain.core.product.OptionCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record ProductOptionInsertRequestDto(

        @NotNull(message = "Option Name cannot be null.")
        OptionName optionName,

        @NotBlank(message = "Option Value cannot be blank.")
        @Size(max = 100, message = "Option Value must be 100 characters or less.")
        String optionValue) {

    public OptionCommand toOption() {
        return new OptionCommand(optionName, optionValue);
    }

}
