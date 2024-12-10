package com.ryuqq.setof.support.external.core.shein.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryuqq.setof.support.external.core.shein.domain.SheInLanguageName;
import com.ryuqq.setof.support.external.core.shein.domain.SheInSite;
import com.ryuqq.setof.support.external.core.shein.domain.SheInSkc;

import java.util.List;

public record SheInProductInsertRequestDto(
        @JsonProperty("brand_code")
        String brandCode,

        @JsonProperty("category_id")
        int categoryId,

        @JsonProperty("edit_type")
        int editType,

        @JsonProperty("product_type_id")
        long productTypeId,

        @JsonProperty("source_system")
        String sourceSystem,

        @JsonProperty("suit_flag")
        int suitFlag,

        @JsonProperty("supplier_code")
        String supplierCode,

        @JsonProperty("multi_language_name_list")
        List<SheInLanguageName> multiLanguageNameList,

        @JsonProperty("skc_list")
        List<SheInSkc> skcList,

        @JsonProperty("site_list")
        List<SheInSite> siteList,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("spu_name")
        String spuName
) {
}
