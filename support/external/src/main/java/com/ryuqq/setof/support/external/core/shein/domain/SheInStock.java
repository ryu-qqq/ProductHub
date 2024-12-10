package com.ryuqq.setof.support.external.core.shein.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SheInStock(
        @JsonProperty("inventory_num") int inventoryNum
) {}
