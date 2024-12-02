package com.ryuqq.setof.support.external.core.buyma.domain;


import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaOptionPayload;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaProductPayload;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaProductInsertRequestDto;
import com.ryuqq.setof.support.external.core.buyma.dto.BuyMaStockUpdateRequestDto;

import java.util.List;

public class BuyMaProductContext implements ExternalMallProductContext {

    private final long productGroupId;
    private final long setOfProductGroupId;
    private final ExternalMallProductDetailContext detailContext;
    private final ExternalMallCategoryAndBrandContext categoryAndBrandContext;
    private final ExternalMallImageGroupContext imageGroupContext;
    private final ExternalMallOptionContext optionContext;
    private final ExternalMallPriceContext priceContext;

    public BuyMaProductContext(long productGroupId, long setOfProductGroupId, ExternalMallProductDetailContext detailContext,
                               ExternalMallCategoryAndBrandContext categoryAndBrandContext,
                               ExternalMallImageGroupContext imageGroupContext,
                               ExternalMallOptionContext optionContext,
                               ExternalMallPriceContext priceContext) {
        this.productGroupId = productGroupId;
        this.setOfProductGroupId = setOfProductGroupId;
        this.detailContext = detailContext;
        this.categoryAndBrandContext = categoryAndBrandContext;
        this.imageGroupContext = imageGroupContext;
        this.optionContext = optionContext;
        this.priceContext = priceContext;
    }

    @Override
    public long getProductGroupId() {
        return productGroupId;
    }

    @Override
    public long getSetOfProductGroupId() {
        return setOfProductGroupId;
    }

    @Override
    public ExternalMallProductDetailContext getDetailContext() {
        return detailContext;
    }

    @Override
    public ExternalMallImageGroupContext getExternalMallImageGroupContext() {
        return imageGroupContext;
    }

    @Override
    public ExternalMallOptionContext getExternalMallOptionContext() {
        return optionContext;
    }

    @Override
    public ExternalMallPriceContext getPriceContext() {
        return priceContext;
    }

    @Override
    public ExternalMallCategoryAndBrandContext getExternalMallCategoryAndBrandContext() {
        return categoryAndBrandContext;
    }

    public int getTotalQuantity(){
        Object customAttributes = optionContext.getCustomAttributes();
        if (customAttributes instanceof BuyMaOptionContext.BuyMaCustomOptions options) {
            return options.variants().stream()
                    .map(BuyMaVariant::stocks)
                    .reduce(0, Integer::sum);

        }
        throw new IllegalArgumentException("Invalid custom attributes type: " + customAttributes.getClass());
    }


    public List<ExternalMallImageRequestResult> toImageRequestResult(){
        return imageGroupContext.getImages().stream()
                .map(i -> new ExternalMallImageRequestResult(
                        i.getImageUrl(),
                        i.getOriginUrl(),
                        i.getOrder()
                ))
                .toList();
    }


    public BuyMaProductInsertRequestDto toProductInsertRequestDto(){
        BuyMaProductDetailContext.BuyMaCustomAttributes customAttributes = (BuyMaProductDetailContext.BuyMaCustomAttributes) detailContext.getCustomAttributes();

        BuyMaProductPayload product = new BuyMaProductPayload(
                customAttributes.referenceNumber(),
                customAttributes.control(),
                detailContext.getExternalMallNameContext().getName(),
                detailContext.getExternalMallNameContext().getDescription(),
                Integer.parseInt(categoryAndBrandContext.getBrandId()),
                categoryAndBrandContext.getBrandName(),
                Integer.parseInt(categoryAndBrandContext.getCategoryId()),
                priceContext.getCurrentPrice().intValue(),
                priceContext.getRegularPrice().intValue(),
                customAttributes.availableUntil(),
                customAttributes.buyingAreaId(),
                customAttributes.shippingAreaId(),
                toImages(),
                customAttributes.buyMaShippingMethods(),
                toVariants(),
                toOptions(),
                getId(customAttributes),
                customAttributes.duty(),
                getOptionComment()
        );
        return new BuyMaProductInsertRequestDto(product);
    }



    public BuyMaStockUpdateRequestDto toStockUpdateRequestDto(){
        BuyMaProductDetailContext.BuyMaCustomAttributes customAttributes = (BuyMaProductDetailContext.BuyMaCustomAttributes) detailContext.getCustomAttributes();
        BuyMaOptionPayload buyMaOptionPayload = new BuyMaOptionPayload(
                customAttributes.referenceNumber(),
                toVariants()
        );

        return new BuyMaStockUpdateRequestDto(buyMaOptionPayload);
    }


    private List<BuyMaImageContext> toImages() {
        return imageGroupContext.getImages().stream()
                .map(image -> new BuyMaImageContext(image.getImageUrl(), image.getOrder()))
                .toList();
    }


    private List<BuyMaOption> toOptions() {
        Object customAttributes = optionContext.getCustomAttributes();
        if (customAttributes instanceof BuyMaOptionContext.BuyMaCustomOptions options) {
            return options.options();
        }
        throw new IllegalArgumentException("Invalid custom attributes type: " + customAttributes.getClass());
    }

    private List<BuyMaVariant> toVariants() {
        Object customAttributes = optionContext.getCustomAttributes();
        if (customAttributes instanceof BuyMaOptionContext.BuyMaCustomOptions options) {
            return options.variants();
        }
        throw new IllegalArgumentException("Invalid custom attributes type: " + customAttributes.getClass());
    }

    private String getOptionComment() {
        Object customAttributes = optionContext.getCustomAttributes();
        if (customAttributes instanceof BuyMaOptionContext.BuyMaCustomOptions options) {
            return options.optionComment();
        }
        throw new IllegalArgumentException("Invalid custom attributes type: " + customAttributes.getClass());
    }


    private Integer getId(BuyMaProductDetailContext.BuyMaCustomAttributes customAttributes){
        Integer id = null;
        if(customAttributes.id() != null & !customAttributes.id().isBlank()){
            id = Integer.parseInt(customAttributes.id());
        }
        return id;
    }


}
