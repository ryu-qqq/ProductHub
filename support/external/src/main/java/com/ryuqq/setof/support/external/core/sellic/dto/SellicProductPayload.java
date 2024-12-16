package com.ryuqq.setof.support.external.core.sellic.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryuqq.setof.support.external.core.*;
import com.ryuqq.setof.support.external.core.sellic.SellicOrigin;

import java.util.List;

public final class SellicProductPayload {
    @JsonProperty("product_name")
    private final String productName;
    @JsonProperty("own_code")
    private final String ownCode;
    @JsonProperty("origin")
    private final int origin;
    @JsonProperty("supplier_name")
    private final String supplierName;
    @JsonProperty("category_id")
    private final int categoryId;
    @JsonProperty("sale_status")
    private final int saleStatus;
    @JsonProperty("delivery_charge_type")
    private final int deliveryChargeType;
    @JsonProperty("delivery_fee")
    private final String deliveryFee;
    @JsonProperty("tax")
    private final int tax;
    @JsonProperty("brand")
    private final String brand;
    @JsonProperty("detail_note")
    private final String detailNote;
    @JsonProperty("market_price")
    private final int marketPrice;
    @JsonProperty("sale_price")
    private final int salePrice;
    @JsonProperty("image1")
    private final String image1;
    @JsonProperty("image2")
    private final String image2;
    @JsonProperty("image3")
    private final String image3;
    @JsonProperty("image4")
    private final String image4;
    @JsonProperty("image5")
    private final String image5;
    @JsonProperty("image6")
    private final String image6;
    @JsonProperty("image7")
    private final String image7;
    @JsonProperty("image8")
    private final String image8;
    @JsonProperty("image9")
    private final String image9;
    @JsonProperty("image10")
    private final String image10;
    @JsonProperty("image11")
    private final String image11;
    @JsonProperty("notify_code")
    private final String notifyCode;
    @JsonProperty("notify1")
    private final String notify1;
    @JsonProperty("notify2")
    private final String notify2;
    @JsonProperty("notify3")
    private final String notify3;
    @JsonProperty("notify4")
    private final String notify4;
    @JsonProperty("notify5")
    private final String notify5;
    @JsonProperty("notify6")
    private final String notify6;
    @JsonProperty("notify7")
    private final String notify7;
    @JsonProperty("notify8")
    private final String notify8;
    @JsonProperty("notify9")
    private final String notify9;
    @JsonProperty("option_name1")
    private final String optionName1;
    @JsonProperty("option_name2")
    private final String optionName2;
    @JsonProperty("product_stocks")
    private final List<SellicOptionDto> productStocks;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("product_id")
    private final Long productId;

    public SellicProductPayload(
            ExternalSyncProductGroup productGroup,
            ExternalMallNameContext externalMallNameContext,
            ExternalMallCategoryAndBrandContext externalMallCategoryAndBrandContext,
            ExternalMallPriceContext externalMallPriceContext,
            SellicOptionContextDto sellicOptionContextDto,
            List<ExternalMallImage> externalMallImages
    ) {
        this.productName = externalMallNameContext.name();
        this.ownCode = String.valueOf(productGroup.setOfProductGroupId());
        this.origin = SellicOrigin.of(productGroup.notice().origin().getDisplayName()).getCode();
        this.supplierName = "";
        this.categoryId = Integer.parseInt(externalMallCategoryAndBrandContext.categoryId());
        this.saleStatus = productGroup.soldOutYn() ? 2002 : 2000;;
        this.deliveryChargeType = 1296;
        this.deliveryFee = "0";
        this.tax = 0;
        this.brand = externalMallCategoryAndBrandContext.brandName();
        this.detailNote = productGroup.detailDescription().detailDescription();
        this.marketPrice = externalMallPriceContext.regularPrice().intValue();
        this.salePrice = externalMallPriceContext.currentPrice().intValue();
        this.image1 = !externalMallImages.isEmpty() ? externalMallImages.getFirst().imageUrl() : "";
        this.image2 = "";
        this.image3 = "";
        this.image4 = "";
        this.image5 = "";
        this.image6 =  externalMallImages.size() > 1 ? externalMallImages.get(1).imageUrl() : "";
        this.image7 =  externalMallImages.size() > 2 ? externalMallImages.get(2).imageUrl() : "";
        this.image8 =  externalMallImages.size() > 3 ? externalMallImages.get(3).imageUrl() : "";
        this.image9 =  externalMallImages.size() > 4 ? externalMallImages.get(4).imageUrl() : "";
        this.image10 = externalMallImages.size() > 5 ? externalMallImages.get(5).imageUrl() : "";
        this.image11 = externalMallImages.size() > 6 ? externalMallImages.get(6).imageUrl() : "";
        this.notifyCode = "1";
        this.notify1 = productGroup.notice().material();
        this.notify2 = productGroup.notice().color();
        this.notify3 = productGroup.notice().size();
        this.notify4 = productGroup.notice().maker();
        this.notify5 = productGroup.notice().origin().getDisplayName();
        this.notify6 = productGroup.notice().washingMethod();
        this.notify7 = productGroup.notice().yearMonth();
        this.notify8 = productGroup.notice().assuranceStandard();
        this.notify9 = productGroup.notice().asPhone();
        this.optionName1 = sellicOptionContextDto.optionName1();
        this.optionName2 = sellicOptionContextDto.optionName2();
        this.productStocks = sellicOptionContextDto.options();
        this.productId = Long.parseLong(productGroup.externalProductGroupId());
    }


}