package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SheInProductInsertRequestDto {
    @JsonProperty("brand_code")
    private final String brandCode;
    @JsonProperty("category_id")
    private final int categoryId;
    @JsonProperty("edit_type")
    private final int editType;
    @JsonProperty("product_type_id")
    private final long productTypeId;
    @JsonProperty("source_system")
    private final String sourceSystem;
    @JsonProperty("suit_flag")
    private final int suitFlag;
    @JsonProperty("supplier_code")
    private final String supplierCode;
    @JsonProperty("multi_language_name_list")
    private final List<SheInLanguageNameDto> multiLanguageNameList;
    @JsonProperty("skc_list")
    private final List<SheInSkcDto> skcList;
    @JsonProperty("site_list")
    private final List<SheInSiteDto> siteList;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("spu_name")
    private final String spuName;

    public SheInProductInsertRequestDto(
            String brandCode,
            String categoryId,
            String productTypeId,
            String styleCode,
            long productGroupId,
            String productName,
            SheInImageGroupDto sheInImageGroupDto,
            List<SheInSkuDto> attributes

    ) {
        this.brandCode = brandCode;
        this.categoryId = Integer.parseInt(categoryId);
        this.editType = 0;
        this.productTypeId = Integer.parseInt(productTypeId);
        this.sourceSystem = "openapi";
        this.suitFlag = 0;
        this.supplierCode = DEFAULT_REFERENCE_NUMBER(styleCode, productGroupId);
        this.multiLanguageNameList = List.of(new SheInLanguageNameDto("en", productName));
        this.skcList = SheInSkcDto.sheInSkcDtos(sheInImageGroupDto, styleCode, attributes);
        this.siteList = List.of(new SheInSiteDto("shein", List.of("shein-us")));
        this.spuName = null;
    }


    private String DEFAULT_REFERENCE_NUMBER(String styleCode, long productGroupId) {
        StringBuilder sb = new StringBuilder();
        sb.append(productGroupId);

        if (styleCode != null && !styleCode.isEmpty()) {
            sb.append("_");
            sb.append(styleCode);
        }
        return sb.toString();
    }


}
