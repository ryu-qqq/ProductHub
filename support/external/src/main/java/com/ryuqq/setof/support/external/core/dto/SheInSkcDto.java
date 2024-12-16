package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class SheInSkcDto {

    @JsonProperty("image_info")
    private final SheInImageGroupDto imageInfo;

    @JsonProperty("supplier_code")
    private final String supplierCode;

    @JsonProperty("sku_list")
    private final List<SheInSkuDto> skuList;

    @JsonProperty("sale_attribute")
    private final SheInProductAttributeDto saleAttribute;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("skc_name")
    private final String skcName;

    public SheInSkcDto(
            SheInImageGroupDto imageInfo,
            String supplierCode,
            List<SheInSkuDto> skuList
    ) {
        this.imageInfo = imageInfo;
        this.supplierCode = supplierCode;
        this.skuList = skuList;
        this.saleAttribute = new SheInProductAttributeDto(27 ,447);
        this.skcName = null;
    }

    public static List<SheInSkcDto> sheInSkcDtos(SheInImageGroupDto sheInImageGroupDto, String styleCode, List<SheInSkuDto> attributes){
            return List.of(new SheInSkcDto(sheInImageGroupDto, styleCode, attributes));
    }

}
