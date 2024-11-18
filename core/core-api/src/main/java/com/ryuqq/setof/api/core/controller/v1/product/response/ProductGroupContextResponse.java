package com.ryuqq.setof.api.core.controller.v1.product.response;

import com.ryuqq.setof.domain.core.product.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductGroupContextResponse(
        ProductGroupResponse productGroup,
        ProductDeliveryResponse productDelivery,
        ProductNoticeResponse productNotice,
        ProductDetailDescriptionResponse productDetailDescription,
        List<ProductGroupImageResponse> productImages,
        Set<ProductResponse> products
) {
    public static ProductGroupContextResponse of(ProductGroupContext productGroupContext) {
        return new ProductGroupContextResponse(
                toProductGroupResponse(productGroupContext.getProductGroup()),
                toProductDeliveryResponse(productGroupContext.getDelivery()),
                toProductNoticeResponse(productGroupContext.getNotice()),
                toProductDetailDescriptionResponse(productGroupContext.getDetailDescription()),
                productGroupContext.getImages().stream()
                        .map(ProductGroupContextResponse::toProductGroupImageResponse)
                        .toList(),
                productGroupContext.getProducts().stream()
                        .map(ProductGroupContextResponse::toProductResponse)
                        .collect(Collectors.toSet())
        );
    }


    private static ProductGroupResponse toProductGroupResponse(ProductGroup productGroup) {
        return new ProductGroupResponse(
                productGroup.getProductGroupId(),
                productGroup.getSellerId(),
                productGroup.getColor(),
                productGroup.getCategories(),
                productGroup.getBrand(),
                productGroup.getProductGroupName(),
                productGroup.getStyleCode(),
                productGroup.getProductCondition(),
                productGroup.getManagementType(),
                productGroup.getOptionType(),
                toPriceResponse(productGroup.getPrice()),
                productGroup.isSoldOutYn(),
                productGroup.isDisplayYn(),
                productGroup.getProductStatus(),
                productGroup.getKeywords()
                );
    }

    private static PriceResponse toPriceResponse(Price price) {
        return new PriceResponse(
                price.getRegularPrice().getAmount(),
                price.getCurrentPrice().getAmount(),
                price.getSalePrice().getAmount(),
                price.getDirectDiscountPrice().getAmount(),
                price.getDirectDiscountRate(),
                price.getDiscountRate()
        );
    }

    private static ProductDeliveryResponse toProductDeliveryResponse(ProductDelivery delivery) {
        return new ProductDeliveryResponse(
                delivery.getDeliveryArea(),
                delivery.getDeliveryFee().getAmount(),
                delivery.getDeliveryPeriodAverage(),
                delivery.getReturnMethodDomestic(),
                delivery.getReturnCourierDomestic(),
                delivery.getReturnChargeDomestic().getAmount(),
                delivery.getReturnExchangeAreaDomestic()
        );
    }

    private static ProductNoticeResponse toProductNoticeResponse(ProductNotice notice) {
        return new ProductNoticeResponse(
                notice.getMaterial(),
                notice.getColor(),
                notice.getSize(),
                notice.getMaker(),
                notice.getOrigin(),
                notice.getWashingMethod(),
                notice.getYearMonth(),
                notice.getAssuranceStandard(),
                notice.getAsPhone()
        );
    }

    private static ProductDetailDescriptionResponse toProductDetailDescriptionResponse(ProductDetailDescription detailDescription) {
        return new ProductDetailDescriptionResponse(detailDescription.getDetailDescription());
    }

    private static ProductGroupImageResponse toProductGroupImageResponse(ProductGroupImage image) {
        return new ProductGroupImageResponse(image.getProductImageType(), image.getImageUrl());
    }

    private static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getProductGroupId(),
                product.getProductId(),
                product.getQuantity(),
                product.isSoldOutYn(),
                product.isDisplayYn(),
                product.getOption(),
                product.getOptions().stream()
                        .map(ProductGroupContextResponse::toProductOptionResponse)
                        .collect(Collectors.toSet()),
                product.getAdditionalPrice().getAmount()
        );
    }

    private static OptionResponse toProductOptionResponse(Option option) {
        return new OptionResponse(
                option.getProductId(),
                option.getOptionGroupId(),
                option.getOptionDetailId(),
                option.getOptionName(),
                option.getOptionValue()
        );
    }

}
