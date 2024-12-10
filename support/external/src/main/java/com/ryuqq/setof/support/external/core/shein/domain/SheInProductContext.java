package com.ryuqq.setof.support.external.core.shein.domain;


import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.shein.dto.SheInImageGroupPayload;
import com.ryuqq.setof.support.external.core.shein.dto.SheInImagePayload;
import com.ryuqq.setof.support.external.core.shein.dto.SheInProductInsertRequestDto;

import java.util.List;

public class SheInProductContext  {

    private final long productGroupId;
    private final long setOfProductGroupId;
    private final ExternalMallProductDetailContext detailContext;
    private final ExternalMallCategoryAndBrandContext categoryAndBrandContext;
    private final ExternalMallImageContext imageGroupContext;
    private final ExternalMallOption optionContext;
    private final ExternalMallPriceContext priceContext;

    public SheInProductContext(long productGroupId, long setOfProductGroupId, ExternalMallProductDetailContext detailContext,
                               ExternalMallCategoryAndBrandContext categoryAndBrandContext,
                               ExternalMallImageContext imageGroupContext,
                               ExternalMallOption optionContext,
                               ExternalMallPriceContext priceContext) {
        this.productGroupId = productGroupId;
        this.setOfProductGroupId = setOfProductGroupId;
        this.detailContext = detailContext;
        this.categoryAndBrandContext = categoryAndBrandContext;
        this.imageGroupContext = imageGroupContext;
        this.optionContext = optionContext;
        this.priceContext = priceContext;
    }

    public int getTotalQuantity(){
        Object customAttributes = optionContext.getCustomAttributes();
        if (customAttributes instanceof SheInOptionContext.SheInCustomOptions options) {
            return options.skus().stream()
                    .flatMap(sheInSku -> sheInSku.stockInfoList().stream())
                    .map(SheInStock::inventoryNum)
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




    public SheInProductInsertRequestDto toSheInProductInsertRequestDto(){
        SheInProductDetailContext.SheInCustomAttributes customAttributes = (SheInProductDetailContext.SheInCustomAttributes) detailContext.getCustomAttributes();

        detailContext.getCustomAttributes();


        return new SheInProductInsertRequestDto(
                categoryAndBrandContext.getBrandId(),
                Integer.parseInt(categoryAndBrandContext.getCategoryId()),
                customAttributes.editType(),
                customAttributes.productTypeId(),
                customAttributes.sourceSystem(),
                customAttributes.suitFlag(),
                customAttributes.supplierCode(),
                customAttributes.multiLanguageNameList(),
                toSkcs(customAttributes.supplierCode()),
                customAttributes.siteList(),
                null
        );
    }


    private List<SheInSkc> toSkcs(String styleCode) {
        Object customAttributes = optionContext.getCustomAttributes();
        if (customAttributes instanceof SheInOptionContext.SheInCustomOptions options) {
            new SheInSkc (
                    getSheInImageGroupContext(),
                    styleCode,
                    options.skus(),
                    List.of(new SheInProductAttribute(27, 447)),
                    null
            );

        }
        throw new IllegalArgumentException("Invalid custom attributes type: " + customAttributes.getClass());
    }




    private SheInImageGroupPayload getSheInImageGroupContext() {
        List<SheInImagePayload> imagePayloads = imageGroupContext.getImages().stream()
                .map(i ->
                        new SheInImagePayload(
                                null,
                                i.getOrder(),
                                i.getOrder() == 1 ? 1 : 2,
                                i.getImageUrl()
                        )
                )
                .toList();

        return new SheInImageGroupPayload(null, imagePayloads);
    }





}
