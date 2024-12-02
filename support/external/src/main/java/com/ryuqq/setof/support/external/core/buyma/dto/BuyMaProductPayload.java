package com.ryuqq.setof.support.external.core.buyma.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaImageContext;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaOption;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaShippingMethod;
import com.ryuqq.setof.support.external.core.buyma.domain.BuyMaVariant;

import java.util.List;

public record BuyMaProductPayload(
        @JsonProperty("reference_number") String referenceNumber,
        @JsonProperty("control") String control,
        @JsonProperty("name") String name,
        @JsonProperty("comments") String comments,
        @JsonProperty("brand_id") int brandId,
        @JsonProperty("brand_name") String brandName,
        @JsonProperty("category_id") int categoryId,
        @JsonProperty("price") int price,
        @JsonProperty("reference_price") int referencePrice,
        @JsonProperty("available_until") String availableUntil,
        @JsonProperty("buying_area_id") int buyingAreaId,
        @JsonProperty("shipping_area_id") int shippingAreaId,
        @JsonProperty("images") List<BuyMaImageContext> images,
        @JsonProperty("shipping_methods") List<BuyMaShippingMethod> shippingMethods,
        @JsonProperty("variants") List<BuyMaVariant> variants,
        @JsonProperty("options") List<BuyMaOption> options,
        @JsonInclude(JsonInclude.Include.NON_NULL) @JsonProperty("id") Integer id,
        @JsonProperty("duty") String duty,
        @JsonProperty("colorsize_comments") String colorSizeComments
) {}