package com.ryuqq.setof.producthub.core.api.controller.v1.product.request;

import com.ryuqq.setof.core.ManagementType;
import com.ryuqq.setof.core.OptionType;
import com.ryuqq.setof.core.ProductCondition;
import com.ryuqq.setof.core.ProductStatus;
import com.ryuqq.setof.domain.core.product.command.*;

import java.math.BigDecimal;
import java.util.List;

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
        boolean displayYn,
        ProductNoticeInsertRequestDto productNotice,
        ProductDeliveryRequestDto productDelivery,
        List<ProductGroupImageRequestDto> productImageList,
        ProductGroupDetailDescriptionRequestDto productGroupDetailDescriptionRequestDto,
        List<ProductInsertRequestDto> productOptions
) {
    public ProductGroupInsertRequestDto {
        validateFields();
    }

    private void validateFields() {
        validateString(productGroupName, 100, "Product Group Name");
        validateString(styleCode, 50, "Style Code");
        validateBigDecimal(regularPrice, BigDecimal.valueOf(100000000), "Regular Price");
        validateBigDecimal(currentPrice, BigDecimal.valueOf(100000000), "Current Price");
        validateObjectNotNull(productNotice, "Product Notice");
        validateObjectNotNull(productDelivery, "Product Delivery");
        validateListNotNullOrEmpty(productImageList, "Product Image List", false);
        validateListNotNullOrEmpty(productOptions, "Product Options", false);
    }

    public ProductGroupCommand toProductGroupCommand() {
        return new ProductGroupCommand(brandId, categoryId, sellerId, productGroupName,
                styleCode, productCondition, managementType, optionType, regularPrice, currentPrice,
                soldOutYn, displayYn, ProductStatus.WAITING);
    }


    public ProductGroupCommandContext toProductGroupCommandContext(){
        ProductGroupCommand productGroup = toProductGroupCommand();
        ProductNoticeCommand notice = productNotice.toProductNotice();
        ProductDeliveryCommand delivery = productDelivery.toProductDelivery();
        List<ProductGroupImageCommand> images = productImageList.stream().map(ProductGroupImageRequestDto::toProductGroupImage).toList();
        ProductDetailDescriptionCommand detailDescription = productGroupDetailDescriptionRequestDto.toProductDetailDescription();
        List<ProductCommand> productCommands = toProductCommands(productGroup.optionType(), productOptions);

        return new ProductGroupCommandContext(productGroup, notice, delivery, images, detailDescription, productCommands);
    }

    public List<ProductCommand> toProductCommands(OptionType optionType, List<ProductInsertRequestDto> productOptions) {
        return productOptions.stream()
                .map(p -> p.toProductCommand(optionType))
                .toList();
    }
}