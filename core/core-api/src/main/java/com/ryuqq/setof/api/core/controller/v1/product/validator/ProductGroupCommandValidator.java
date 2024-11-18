package com.ryuqq.setof.api.core.controller.v1.product.validator;

import com.ryuqq.setof.api.core.controller.v1.product.request.ProductGroupCommandContextRequestDto;
import com.ryuqq.setof.api.core.controller.v1.product.request.ProductGroupImageRequestDto;
import com.ryuqq.setof.api.core.controller.v1.product.request.ProductInsertRequestDto;
import com.ryuqq.setof.api.core.controller.v1.product.request.ProductOptionInsertRequestDto;
import com.ryuqq.setof.enums.core.OptionName;
import com.ryuqq.setof.enums.core.OptionType;
import com.ryuqq.setof.enums.core.ProductImageType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductGroupCommandValidator implements ConstraintValidator<ProductGroupCommandValidate, ProductGroupCommandContextRequestDto> {

    @Override
    public boolean isValid(ProductGroupCommandContextRequestDto requestDto, ConstraintValidatorContext context) {
        boolean isValid = validateProductGroupImages(requestDto.productImageList(), context);
        isValid &= validateProductOptions(requestDto.productGroup().optionType(), requestDto.productOptions(), context);
        return isValid;
    }

    private boolean validateProductGroupImages(List<ProductGroupImageRequestDto> images, ConstraintValidatorContext context) {
        long mainImageCount = images.stream()
                .map(ProductGroupImageRequestDto::productImageType)
                .filter(type -> type == ProductImageType.MAIN)
                .count();

        if (mainImageCount == 0) {
            addConstraintViolation(context, "At least one MAIN image is required.");
            return false;
        }

        if (mainImageCount > 1) {
            addConstraintViolation(context, "Only one MAIN image is allowed.");
            return false;
        }
        return true;
    }

    private boolean validateProductOptions(OptionType optionType, List<ProductInsertRequestDto> productOptions, ConstraintValidatorContext context) {
        if (!optionType.isMultiOption() && productOptions.size() > 1) {
            addConstraintViolation(context, "Single option type can only have one product.");
            return false;
        }

        return productOptions
                .stream()
                .allMatch(p -> validateOption(optionType, p.options(), context));
    }

    private boolean validateOption(OptionType optionType, List<ProductOptionInsertRequestDto> options, ConstraintValidatorContext context) {
        switch (optionType) {
            case SINGLE -> {
                if (!options.isEmpty()) {
                    addConstraintViolation(context, "Single option type does not allow options.");
                    return false;
                }
            }
            case OPTION_ONE -> {
                Set<OptionName> optionNames = toOptionNameSet(options);
                if (optionNames.size() != 1 || options.size() > 1) {
                    addConstraintViolation(context, "One step option type should have only one unique OptionName.");
                    return false;
                }
            }
            case OPTION_TWO -> {
                Set<OptionName> optionNames = toOptionNameSet(options);
                if (optionNames.size() != 2 || !isValidTwoStepOptionCombination(optionNames)) {
                    addConstraintViolation(context, "Two step options must be a valid combination of Color and Size, or Default_One and Default_Two.");
                    return false;
                }
            }
        }
        return true;
    }

    private Set<OptionName> toOptionNameSet(List<ProductOptionInsertRequestDto> options) {
        return options.stream()
                .map(ProductOptionInsertRequestDto::optionName)
                .collect(Collectors.toSet());
    }

    private boolean isValidTwoStepOptionCombination(Set<OptionName> optionNames) {
        return (optionNames.contains(OptionName.COLOR) && optionNames.contains(OptionName.SIZE)) ||
                (optionNames.contains(OptionName.DEFAULT_ONE) && optionNames.contains(OptionName.DEFAULT_TWO));
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }

}
