package com.ryuqq.setof.support.external.core.buyma.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryuqq.setof.support.external.core.ExternalMallImagePayload;
import com.ryuqq.setof.support.external.core.ExternalMallProductImageContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public record BuyMaProduct(
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
        @JsonProperty("images") List<BuyMaImage> images,
        @JsonProperty("shipping_methods") List<BuyMaShippingMethod> shippingMethods,
        @JsonProperty("variants") List<BuyMaVariant> variants,
        @JsonProperty("options") List<BuyMaOption> options,
        @JsonInclude(JsonInclude.Include.NON_NULL) @JsonProperty("id") Integer id,
        @JsonProperty("duty") String duty,
        @JsonProperty("colorsize_comments") String colorSizeComments
) implements ExternalMallImagePayload {

    public BuyMaProduct(String styleCode, long productGroupId, String name, String comments, int brandId, String brandName, int categoryId, int price, int referencePrice, List<BuyMaImage> images, List<BuyMaVariant> variants, List<BuyMaOption> options,  Integer id, String colorSizeComments) {
        this(
                referenceNumber(styleCode, productGroupId),
                "publish",
                name,
                comments,
                brandId,
                brandName,
                categoryId,
                price,
                referencePrice,
                LocalDateTime.now().plusDays(90).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                2002003001,
                2002003001,
                images,
                defaultBuyMaShippingMethods(),
                variants,
                options,
                id,
                "included",
                colorSizeComments
        );
    }

    private static List<BuyMaShippingMethod> defaultBuyMaShippingMethods() {
        List<BuyMaShippingMethod> defaultBuyMaShippingMethods = new ArrayList<>();
        defaultBuyMaShippingMethods.add(new BuyMaShippingMethod(984481));
        defaultBuyMaShippingMethods.add(new BuyMaShippingMethod(984491));
        return defaultBuyMaShippingMethods;
    }

    private static String referenceNumber(String styleCode, long productGroupId) {
        StringBuilder sb = new StringBuilder();

        sb.append(productGroupId);

        if (styleCode != null && !styleCode.isEmpty()) {
            sb.append("_");
            sb.append(styleCode);
        }
        return sb.toString();
    }


    @Override
    public List<? extends ExternalMallProductImageContext> getExternalMallProductImageContext() {
        return images;
    }
}