package com.ryuqq.setof.support.external.core.shein.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryuqq.setof.support.external.core.shein.dto.SheInImageGroupPayload;

import java.util.List;

public record SheInSkc(
        @JsonProperty("image_info")
        SheInImageGroupPayload imageInfo,

        @JsonProperty("supplier_code")
        String supplierCode,

        @JsonProperty("sku_list")
        List<SheInSku> skuList,

        @JsonProperty("sale_attribute")
        List<SheInProductAttribute> saleAttribute,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("skc_name")
        String skcName
) {}
