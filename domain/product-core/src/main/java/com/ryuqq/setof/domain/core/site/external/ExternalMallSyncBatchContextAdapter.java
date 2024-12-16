package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.*;
import com.ryuqq.setof.domain.core.product.gpt.GptOptionsResult;
import com.ryuqq.setof.domain.core.site.StandardSize;
import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ExternalMallSyncBatchContextAdapter {

    public List<ExternalMallPreProductContext> toExternalMallContexts(ExternalSyncBatchContext externalSyncBatchContext){
        return externalSyncBatchContext
                .syncData()
                    .stream()
                        .map(p ->
                                new ExternalMallPreProductContext(
                                        p.externalProductGroup().siteId(),
                                        getSiteNameEnum(externalSyncBatchContext.externalPolicyContext()),
                                        toExternalSyncBrand(p.mappingBrand()),
                                        toExternalSyncCategory(p.mappingCategory()),
                                        toExternalSyncProductGroup(p.productGroupContext(), externalSyncBatchContext.externalPolicyContext().productPolicy(), p.externalProductGroup()),
                                        toExternalSyncProducts(p.productGroupContext().getProducts()),
                                        toExternalSyncCategoryOptions(p.externalCategoryOptions()),
                                        toExternalSyncOptionResult(p.gptOptionsResult()),
                                        toExternalSyncStandardSizes(p.standardSizes())
                                )
                        ).toList();
    }

    private SiteName getSiteNameEnum(ExternalPolicyContext externalPolicyContext){
        String name = externalPolicyContext.externalPolicy().siteName();
        return SiteName.of(name);
    }

    private ExternalSyncBrand toExternalSyncBrand(MappingBrand mappingBrand){
        return new ExternalSyncBrand(mappingBrand.externalBrandId(), mappingBrand.brandName());
    }

    private ExternalSyncCategory toExternalSyncCategory(MappingCategory mappingCategory){
        return new ExternalSyncCategory(
                mappingCategory.siteId(),
                mappingCategory.externalCategoryId(),
                mappingCategory.externalExtraCategoryId(),
                mappingCategory.description(),
                mappingCategory.categoryId(),
                mappingCategory.categoryName(),
                mappingCategory.targetGroup(),
                mappingCategory.categoryType());
    }

    private ExternalSyncProductGroup toExternalSyncProductGroup(ProductGroupContext productGroupContext, ExternalProductPolicy externalProductPolicy, ExternalProductGroup externalProductGroup){
        return new ExternalSyncProductGroup(
                productGroupContext.getProductGroup().productGroupId(),
                productGroupContext.getProductGroup().setOfProductGroupId(),
                externalProductGroup.externalProductGroupId(),
                productGroupContext.getProductGroup().sellerId(),
                productGroupContext.getProductGroup().colorIds(),
                productGroupContext.getProductGroup().productGroupName(),
                getName(externalProductPolicy.countryCode(), productGroupContext.getProductGroup().productGroupName(), productGroupContext.getConfig()),
                productGroupContext.getProductGroup().styleCode(),
                productGroupContext.getProductGroup().optionType(),
                productGroupContext.getProductGroup().price().getRegularPrice(),
                productGroupContext.getProductGroup().price().getCurrentPrice(),
                productGroupContext.getProductGroup().soldOutYn(),
                productGroupContext.getProductGroup().displayYn(),
                productGroupContext.getProductGroup().keywords(),
                toExternalSyncProductDelivery(productGroupContext.getProductGroup().delivery()),
                toExternalSyncProductNotice(productGroupContext.getProductGroup().notice()),
                toExternalSyncProductImages(productGroupContext.getProductGroup().images()),
                toExternalSyncProductDetailDescription(productGroupContext.getProductGroup().detailDescription())
        );
    }

    private String getName(Origin countryCode, String productName, ProductGroupConfigContext config) {
        return config.getProductGroupNameConfigs().stream()
                .filter(c -> c.countryCode().equals(countryCode))
                .map(ProductGroupNameConfig::customName)
                .findFirst()
                .orElse(productName);
    }


    private ExternalSyncProductDelivery toExternalSyncProductDelivery(ProductDelivery productDelivery){
        return new ExternalSyncProductDelivery(
                productDelivery.getDeliveryArea(),
                productDelivery.getDeliveryFee(),
                productDelivery.getDeliveryPeriodAverage(),
                productDelivery.getReturnMethodDomestic(),
                productDelivery.getReturnCourierDomestic(),
                productDelivery.getReturnChargeDomestic(),
                productDelivery.getReturnExchangeAreaDomestic()
        );
    }

    private ExternalSyncProductNotice toExternalSyncProductNotice(ProductNotice productNotice){
        return new ExternalSyncProductNotice(
                productNotice.getMaterial(),
                productNotice.getColor(),
                productNotice.getSize(),
                productNotice.getMaker(),
                productNotice.getOrigin(),
                productNotice.getWashingMethod(),
                productNotice.getYearMonth(),
                productNotice.getAssuranceStandard(),
                productNotice.getAsPhone()
        );
    }

    private List<ExternalSyncProductImage> toExternalSyncProductImages(List<ProductGroupImage> images) {
        return images.stream()
                .map(i -> new ExternalSyncProductImage(i.getProductImageType(), i.getImageUrl(), i.getOriginUrl()))
                .distinct()
                .toList();
    }


    private ExternalSyncProductDetailDescription toExternalSyncProductDetailDescription(ProductDetailDescription productDetailDescription){
        return new ExternalSyncProductDetailDescription(productDetailDescription.getDetailDescription());
    }


    private List<ExternalSyncProduct> toExternalSyncProducts(List<Product> products){
        return products.stream().map(p -> new ExternalSyncProduct(
                p.getQuantity(),
                p.isSoldOutYn(),
                p.isDisplayYn(),
                p.getOption(),
                toExternalSyncOptions(p.getOptions()),
                p.getAdditionalPrice()
                ))
                .toList();
    }

    private List<ExternalSyncOption> toExternalSyncOptions(Set<Option> options){
        return options.stream()
                .map(o -> new ExternalSyncOption(o.getOptionName(), o.getOptionValue()))
                .toList();
    }


    private List<ExternalSyncCategoryOption> toExternalSyncCategoryOptions(List<ExternalCategoryOption> categoryOptions){
        return categoryOptions.stream()
                .map(c -> new ExternalSyncCategoryOption(c.siteId(), c.externalCategoryId(), c.optionGroupId(), c.optionId(), c.optionValue()))
                .toList();
    }

    private ExternalSyncOptionResult toExternalSyncOptionResult(GptOptionsResult gptOptionsResult) {
        if (gptOptionsResult != null) {
            return new ExternalSyncOptionResult(
                    gptOptionsResult.productGroupId(),
                    gptOptionsResult.originalOptions(),
                    gptOptionsResult.normalizedOptions() != null
                            ? new ExternalSyncOptionResult.NormalizedOptions(
                            gptOptionsResult.normalizedOptions().sizes(),
                            gptOptionsResult.normalizedOptions().unit()
                    )
                            : null
            );
        }
        return null;
    }

    private List<ExternalSyncStandardSize> toExternalSyncStandardSizes(List<StandardSize> standardSizes){
        return standardSizes.stream().map(
                s -> new ExternalSyncStandardSize(
                        s.categoryId(),
                        s.regionId(),
                        s.name(),
                        s.sizeValue()
                )
            )
                .toList();
    }

}
