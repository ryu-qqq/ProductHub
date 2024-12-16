package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SheInSkuDto(
        @JsonProperty("height") int height,
        @JsonProperty("length") int length,
        @JsonProperty("width") int width,
        @JsonProperty("weight") int weight,
        @JsonProperty("mall_state") int mallState,
        @JsonProperty("stop_purchase") int stopPurchase,
        @JsonProperty("supplier_sku") String supplierSku,
        @JsonProperty("price_info_list") List<SheInPriceDto> priceInfoList,
        @JsonProperty("stock_info_list") List<SheInStockDto>  stockInfoList,
        @JsonProperty("sale_attribute_list") List<SheInProductAttributeDto> saleAttributeList
) {}
