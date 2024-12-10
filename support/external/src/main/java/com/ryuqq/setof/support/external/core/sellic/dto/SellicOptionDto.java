package com.ryuqq.setof.support.external.core.sellic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record SellicOptionDto(
         @JsonProperty("option_item1")  String optionItem1,
         @JsonProperty("option_item2")  String optionItem2,
         @JsonProperty("stock_name")    String stockName,
         @JsonProperty("sale_status")   int saleStatus,
         @JsonProperty("add_price")     BigDecimal addPrice,
         @JsonProperty("present_stock") int presentStock
) {
}
