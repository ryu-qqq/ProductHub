package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.domain.core.brand.MappingBrand;
import com.ryuqq.setof.domain.core.category.MappingCategory;
import com.ryuqq.setof.domain.core.product.*;
import com.ryuqq.setof.domain.core.product.gpt.GptOptionsResult;
import com.ryuqq.setof.enums.core.Origin;
import com.ryuqq.setof.enums.core.SiteName;
import com.ryuqq.setof.support.external.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ExternalMallSyncBatchContextAdapter {

    public List<ExternalMallProductContext> toExternalMallContexts(ExternalSyncBatchContext externalSyncBatchContext){


        return externalSyncBatchContext.syncData().stream()
                .map(p -> new ExternalMallProductContext(
                        p.externalProduct().siteId(),
                        getSiteNameEnum(externalSyncBatchContext.externalPolicyContext()),
                        toExternalMallBrand(p.mappingBrand()),
                        toExternalMallCategory(p.mappingCategory()),
                        toExternalMallProductGroup(p.productGroupContext(), externalSyncBatchContext.externalPolicyContext().productPolicy(), p.externalProduct()),
                        toExternalMallProducts(p.productGroupContext().getProducts()),
                        toExternalMallCategoryOptions(p.externalCategoryOptions()),
                        toOptionProcessResult(p.gptOptionsResult())
                        ))
                .toList();


    }


    private SiteName getSiteNameEnum(ExternalPolicyContext externalPolicyContext){
        String name = externalPolicyContext.externalPolicy().siteName();
        return SiteName.of(name);
    }

    private ExternalMallBrand toExternalMallBrand(MappingBrand mappingBrand){
        return new ExternalMallBrand(mappingBrand.externalBrandId(), mappingBrand.brandName());
    }

    private ExternalMallCategory toExternalMallCategory(MappingCategory mappingCategory){
        return new ExternalMallCategory(mappingCategory.externalCategoryId(), mappingCategory.categoryName(), mappingCategory.targetGroup(), mappingCategory.categoryType());
    }

    private ExternalMallProductGroup toExternalMallProductGroup(ProductGroupContext productGroupContext, ExternalProductPolicy externalProductPolicy, ExternalProduct externalProduct){
        String externalProductId = "";
        String translatedName = getName(externalProductPolicy.countryCode(), productGroupContext.getProductGroup().productGroupName(), productGroupContext.getConfig());

        if(externalProduct.status().isApproved()){
            externalProductId = externalProduct.externalProductId();
            translatedName = externalProduct.productName();
        }

        return new ExternalMallProductGroup(
                productGroupContext.getProductGroup().productGroupId(),
                productGroupContext.getProductGroup().setOfProductGroupId(),
                externalProductId,
                productGroupContext.getProductGroup().sellerId(),
                productGroupContext.getProductGroup().colorIds(),
                productGroupContext.getProductGroup().productGroupName(),
                translatedName,
                productGroupContext.getProductGroup().styleCode(),
                productGroupContext.getProductGroup().optionType(),
                productGroupContext.getProductGroup().price().getRegularPrice(),
                productGroupContext.getProductGroup().price().getCurrentPrice(),
                productGroupContext.getProductGroup().soldOutYn(),
                productGroupContext.getProductGroup().displayYn(),
                productGroupContext.getProductGroup().keywords(),
                toExternalMallProductDelivery(productGroupContext.getProductGroup().delivery()),
                toExternalMallproductNotice(productGroupContext.getProductGroup().notice()),
                toExternalMallProductImages(productGroupContext.getProductGroup().images()),
                toExternalMallProductDetailDescription(productGroupContext.getProductGroup().detailDescription())
        );
    }

    private String getName(Origin countryCode, String productName, ProductGroupConfigContext config) {
        return config.getProductGroupNameConfigs().stream()
                .filter(c -> c.countryCode().equals(countryCode))
                .map(ProductGroupNameConfig::customName)
                .findFirst()
                .orElse(productName);
    }




    private ExternalMallProductDelivery toExternalMallProductDelivery(ProductDelivery productDelivery){
        return new ExternalMallProductDelivery(
                productDelivery.getDeliveryArea(),
                productDelivery.getDeliveryFee(),
                productDelivery.getDeliveryPeriodAverage(),
                productDelivery.getReturnMethodDomestic(),
                productDelivery.getReturnCourierDomestic(),
                productDelivery.getReturnChargeDomestic(),
                productDelivery.getReturnExchangeAreaDomestic()
        );
    }

    private ExternalMallProductNotice toExternalMallproductNotice(ProductNotice productNotice){
        return new ExternalMallProductNotice(
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

    private List<ExternalMallProductImage> toExternalMallProductImages(List<ProductGroupImage> images) {
        return images.stream()
                .map(i -> new ExternalMallProductImage(i.getProductImageType(), i.getImageUrl(), i.getOriginUrl()))
                .distinct()
                .toList();
    }


    private ExternalMallProductDetailDescription toExternalMallProductDetailDescription(ProductDetailDescription productDetailDescription){
        return new ExternalMallProductDetailDescription(productDetailDescription.getDetailDescription());
    }


    private List<ExternalMallProduct> toExternalMallProducts(List<Product> products){
        return products.stream().map(p -> new ExternalMallProduct(
                p.getQuantity(),
                p.isSoldOutYn(),
                p.isDisplayYn(),
                p.getOption(),
                toExternalMallOptions(p.getOptions()),
                p.getAdditionalPrice()
                ))
                .toList();
    }

    private List<ExternalMallOption> toExternalMallOptions(Set<Option> options){
        return options.stream()
                .map(o -> new ExternalMallOption(o.getOptionName(), o.getOptionValue()))
                .toList();
    }


    private List<ExternalMallCategoryOption> toExternalMallCategoryOptions(List<ExternalCategoryOption> categoryOptions){
        return categoryOptions.stream()
                .map(c -> new ExternalMallCategoryOption(c.siteId(), c.externalCategoryId(), c.optionId(), c.optionValue()))
                .toList();
    }

    private ProcessingOptionResult toOptionProcessResult(GptOptionsResult gptOptionsResult) {
        if (gptOptionsResult != null) {
            return new ProcessingOptionResult(
                    gptOptionsResult.productGroupId(),
                    gptOptionsResult.originalOptions(),
                    gptOptionsResult.normalizedOptions() != null
                            ? new ProcessingOptionResult.NormalizedOptions(
                            gptOptionsResult.normalizedOptions().sizes(),
                            gptOptionsResult.normalizedOptions().unit()
                    )
                            : null
            );
        }
        return null;
    }

}
