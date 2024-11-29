package com.ryuqq.setof.support.external.core.shein.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SheInCategoryResponse(
        @JsonProperty("category_id")
        int categoryId,
        @JsonProperty("product_type_id")
        int productTypeId,
        @JsonProperty("parent_category_id")
        int parentCategoryId,
        @JsonProperty("category_name")
        String categoryName,
        @JsonProperty("last_category")
        boolean lastCategory,
        @JsonProperty("children")
        List<SheInCategoryResponse> children
) {}
