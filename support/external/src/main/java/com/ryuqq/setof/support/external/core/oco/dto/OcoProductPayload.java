package com.ryuqq.setof.support.external.core.oco.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryuqq.setof.support.external.core.ExternalMallCategoryAndBrandContext;
import com.ryuqq.setof.support.external.core.ExternalMallNameContext;
import com.ryuqq.setof.support.external.core.ExternalMallPriceContext;
import com.ryuqq.setof.support.external.core.ExternalSyncProductGroup;

import java.util.List;

public final class OcoProductPayload {
    @JsonProperty("pgid")
    private final int pgid;
    @JsonProperty("pcid")
    private final int pcid;
    @JsonProperty("product_type")
    private final String productType;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("madein")
    private final String madeIn;
    @JsonProperty("manufacture")
    private final String manufacture;
    @JsonProperty("info_material")
    private final String infoMaterial;
    @JsonProperty("info_color")
    private final String infoColor;
    @JsonProperty("info_as_tel")
    private final String infoAsTel;
    @JsonProperty("info_qa_tel")
    private final String infoQaTel;
    @JsonProperty("info_addr")
    private final String infoAddr;
    @JsonProperty("code")
    private final String code;
    @JsonProperty("originprice")
    private final int originPrice;
    @JsonProperty("price")
    private final int price;
    @JsonProperty("sale_price_yn")
    private final String salePriceYn;
    @JsonProperty("sale_time_yn")
    private final String saleTimeYn;
    @JsonProperty("stock")
    private final int stock;
    @JsonProperty("keyword")
    private final String keyword;
    @JsonProperty("soldout")
    private final int soldOut;
    @JsonProperty("find_yn")
    private final String findYn;
    @JsonProperty("use_coupon_yn")
    private final String useCouponYn;
    @JsonProperty("hidden")
    private final int hidden;
    @JsonProperty("product_naver_yn")
    private final String productNaverYn;
    @JsonProperty("option_yn")
    private final String optionYn;
    @JsonProperty("option_input_yn")
    private final String optionInputYn;
    @JsonProperty("option_count")
    private final int optionCount;
    @JsonProperty("deliveryprice_free_yn")
    private final String deliveryPriceFreeYn;
    @JsonProperty("re_delivery_view_yn")
    private final String reDeliveryViewYn;
    @JsonProperty("file_list")
    private final List<OcoImageDto> fileList;
    @JsonProperty("main_image_path")
    private final String mainImagePath;
    @JsonProperty("content")
    private final String content;
    @JsonProperty("option_list")
    private final List<OcoOptionDto> optionList;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("pid")
    private final Integer pid;
    @JsonProperty("sale_price")
    private final Integer salePrice;
    @JsonProperty("sale_start_day")
    private final String saleStartDay;
    @JsonProperty("sale_end_day")
    private final String saleEndDay;
    @JsonProperty("sale_time_start_date")
    private final String saleTimeStartDate;
    @JsonProperty("sale_time_end_date")
    private final String saleTimeEndDate;

    public OcoProductPayload(
            ExternalSyncProductGroup productGroup,
            ExternalMallNameContext externalMallNameContext,
            ExternalMallCategoryAndBrandContext externalMallCategoryAndBrandContext,
            ExternalMallPriceContext externalMallPriceContext,
            OcoOptionContextDto ocoOptionContextDto,
            List<OcoImageDto> ocoImageDto
    ) {

        this.pgid = Integer.parseInt(externalMallCategoryAndBrandContext.brandId());
        this.pcid = Integer.parseInt(externalMallCategoryAndBrandContext.categoryId());
        this.productType = "N";
        this.name = externalMallNameContext.name();
        this.madeIn = productGroup.notice().origin().getDisplayName();
        this.manufacture = externalMallCategoryAndBrandContext.brandName();
        this.infoMaterial = productGroup.notice().material();
        this.infoColor = productGroup.notice().color();
        this.infoAsTel = productGroup.notice().asPhone();
        this.infoQaTel = productGroup.notice().asPhone();
        this.infoAddr = productGroup.delivery().deliveryArea();
        this.code = String.valueOf(productGroup.setOfProductGroupId());
        this.originPrice = externalMallPriceContext.regularPrice().intValue();
        this.price = externalMallPriceContext.currentPrice().intValue();
        this.salePriceYn = "N";
        this.saleTimeYn = "N";
        this.stock = ocoOptionContextDto.getTotalQuantity();
        this.keyword = externalMallNameContext.description();
        this.soldOut =  productGroup.soldOutYn() ? 1 : 0;
        this.findYn = productGroup.displayYn() ? "Y" : "N";
        this.useCouponYn = "Y";
        this.hidden = productGroup.displayYn() ? 1 : 0;
        this.productNaverYn = "Y";
        this.optionYn = ocoOptionContextDto.optionProduct() ? "Y" : "N";
        this.optionInputYn = "N";
        this.optionCount = ocoOptionContextDto.optionLength();
        this.deliveryPriceFreeYn = "Y";
        this.reDeliveryViewYn = "N";
        this.fileList = ocoImageDto;
        this.mainImagePath = ocoImageDto.getFirst().relativePath();
        this.content = productGroup.detailDescription().detailDescription();
        this.optionList = ocoOptionContextDto.ocoOptionDto();
        this.pid = Integer.valueOf(productGroup.externalProductGroupId());
        this.salePrice = null;
        this.saleStartDay = null;
        this.saleEndDay = null;
        this.saleTimeStartDate = null;
        this.saleTimeEndDate = null;
    }

}