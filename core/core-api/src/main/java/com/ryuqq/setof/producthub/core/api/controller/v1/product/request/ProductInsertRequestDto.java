package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.OptionName;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.domain.core.product.command.OptionCommand;
import com.ryuqq.setof.domain.core.product.command.ProductCommand;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ryuqq.setof.producthub.core.api.controller.ValidationUtils.*;

public record ProductInsertRequestDto(
        boolean soldOutYn,
        boolean displayYn,
        int quantity,
        BigDecimal additionalPrice,
        List<ProductOptionInsertRequestDto> options
) {

    public ProductInsertRequestDto {
        validateFields(quantity, additionalPrice, options);
    }

    private void validateFields(
                                int quantity,
                                BigDecimal additionalPrice,
                                List<ProductOptionInsertRequestDto> options) {
        validateInt(quantity, 999, "Quantity");
        validateBigDecimal(additionalPrice, BigDecimal.valueOf(100000000), "Additional Price");
        validateListNotNullOrEmpty(options, "Options", true);
    }

    public ProductCommand toProductCommand(OptionType optionType) {

        validOption(optionType);

        List<OptionCommand> optionCommands = options.stream()
                .map(ProductOptionInsertRequestDto::toOption)
                .toList();

        return new ProductCommand(soldOutYn, displayYn, quantity, additionalPrice, optionCommands);
    }



    private void validOption(OptionType optionType){
        switch (optionType) {
            case SINGLE -> {
                if (!options.isEmpty()) {
                    throw new IllegalArgumentException("Single option type does not allow options.");
                }
            }
            case OPTION_ONE -> {
                Set<OptionName> optionNames = toOptionNameSet(options);
                if (optionNames.size() != 1) {
                    throw new IllegalArgumentException("One step option type should have only one unique OptionName.");
                }
            }
            case OPTION_TWO -> {
                Set<OptionName> optionNames = toOptionNameSet(options);
                if (optionNames.size() != 2 || !isValidTwoStepOptionCombination(optionNames)) {
                    throw new IllegalArgumentException("Two step options must be a valid combination of Color and Size, or Default_One and Default_Two.");
                }
            }
        }
    }


    private Set<OptionName> toOptionNameSet(List<ProductOptionInsertRequestDto> options){
        return options.stream()
                .map(ProductOptionInsertRequestDto::optionName)
                .collect(Collectors.toSet());
    }

    private boolean isValidTwoStepOptionCombination(Set<OptionName> optionNames) {
        return (optionNames.contains(OptionName.COLOR) && optionNames.contains(OptionName.SIZE)) ||
                (optionNames.contains(OptionName.DEFAULT_ONE) && optionNames.contains(OptionName.DEFAULT_TWO));
    }


}
