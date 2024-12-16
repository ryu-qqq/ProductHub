package com.ryuqq.setof.support.external.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SheInStockDto(
        @JsonProperty("inventory_num") int inventoryNum
) {}
