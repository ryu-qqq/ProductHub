package com.ryuqq.setof.api.core.controller.v1.product.mapper;

import com.ryuqq.setof.api.core.controller.v1.brand.response.BrandResponse;
import com.ryuqq.setof.api.core.controller.v1.category.response.CategoryResponse;
import com.ryuqq.setof.api.core.controller.v1.product.response.*;
import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.product.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductGroupContextResponseMapper {

    public ProductGroupContextResponse toResponse(ProductGroupContext context) {
        return new ProductGroupContextResponse(
                toProductGroupResponse(context.getProductGroup()),
                toProductResponses(context.getProducts()),
                toCategoryResponseOf(context.getCategories()),
                toBrandResponseOf(context.getBrand()),
                toConfigContextResponse(context.getConfig())
        );
    }

    public List<CategoryResponse> toCategoryResponseOf(List<Category> categories) {
        return categories.stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());

    }

    public BrandResponse toBrandResponseOf(Brand brand) {
        return BrandResponse.of(brand);
    }


    public ProductGroupResponse toProductGroupResponse(ProductGroup productGroup) {
        return new ProductGroupResponse(
                productGroup.productGroupId(),
                productGroup.sellerId(),
                productGroup.productGroupName(),
                productGroup.styleCode(),
                productGroup.productCondition(),
                productGroup.managementType(),
                productGroup.optionType(),
                toPriceResponse(productGroup.price()),
                productGroup.soldOutYn(),
                productGroup.displayYn(),
                productGroup.productStatus(),
                productGroup.keywords(),
                toProductDeliveryResponse(productGroup.delivery()),
                toProductNoticeResponse(productGroup.notice()),
                toProductDetailDescriptionResponse(productGroup.detailDescription()),
                toProductGroupImageResponses(productGroup.images())
                );
    }

    public ProductGroupConfigContextResponse toConfigContextResponse(ProductGroupConfigContext configContext){
        return new ProductGroupConfigContextResponse(
                new ProductGroupConfigResponse(
                        configContext.getConfigId(),
                        configContext.getProductGroupId(),
                        configContext.getProductGroupConfig().isActiveYn()
                ),
                toNameConfigResponse(configContext.getProductGroupNameConfigs()),
                List.of()
        );
    }

    public List<ProductGroupNameConfigResponse> toNameConfigResponse(List<ProductGroupNameConfig> nameConfigs){
        return nameConfigs.stream()
                .map(n -> new ProductGroupNameConfigResponse(
                        n.countryCode(),
                        n.customName()
                ))
                .toList();
    }


    public PriceResponse toPriceResponse(Price price) {
        return new PriceResponse(
                price.getRegularPrice().getAmount(),
                price.getCurrentPrice().getAmount(),
                price.getSalePrice().getAmount(),
                price.getDirectDiscountPrice().getAmount(),
                price.getDirectDiscountRate(),
                price.getDiscountRate()
        );
    }

    public ProductDeliveryResponse toProductDeliveryResponse(ProductDelivery delivery) {
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

    public ProductNoticeResponse toProductNoticeResponse(ProductNotice notice) {
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

    public ProductDetailDescriptionResponse toProductDetailDescriptionResponse(ProductDetailDescription detailDescription) {
        return new ProductDetailDescriptionResponse(detailDescription.getDetailDescription());
    }

    public List<ProductGroupImageResponse> toProductGroupImageResponses(List<ProductGroupImage> images) {
        return images.stream()
                .map(i -> new ProductGroupImageResponse(i.getProductImageType(), i.getImageUrl()))
                .toList();
    }

    public List<ProductResponse> toProductResponses(List<Product> products) {
        return products.stream().map(p -> new ProductResponse(
                p.getProductGroupId(),
                p.getProductId(),
                p.getQuantity(),
                p.isSoldOutYn(),
                p.isDisplayYn(),
                p.getOption(),
                p.getOptions().stream()
                        .map(this::toProductOptionResponse)
                        .collect(Collectors.toSet()),
                p.getAdditionalPrice().getAmount()
        )).toList();

    }

    public OptionResponse toProductOptionResponse(Option option) {
        return new OptionResponse(
                option.getProductId(),
                option.getOptionGroupId(),
                option.getOptionDetailId(),
                option.getOptionName(),
                option.getOptionValue()
        );
    }


}
