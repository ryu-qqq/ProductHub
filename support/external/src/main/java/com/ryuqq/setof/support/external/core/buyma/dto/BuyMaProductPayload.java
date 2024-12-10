package com.ryuqq.setof.support.external.core.buyma.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BuyMaProductPayload{
        @JsonProperty("reference_number")
        private String referenceNumber;

        @JsonProperty("control")
        private String control;

        @JsonProperty("name")
        private String name;

        @JsonProperty("comments")
        private String comments;

        @JsonProperty("brand_id")
        private int brandId;

        @JsonProperty("brand_name")
        private String brandName;

        @JsonProperty("category_id")
        private int categoryId;

        @JsonProperty("price")
        private int price;

        @JsonProperty("reference_price")
        private int referencePrice;

        @JsonProperty("available_until")
        private String availableUntil;

        @JsonProperty("buying_area_id")
        private  int buyingAreaId;

        @JsonProperty("shipping_area_id")
        private int shippingAreaId;

        @JsonProperty("images")
        private List<BuyMaImageDto> images;

        @JsonProperty("shipping_methods")
        private List<BuyMaShippingMethodDto> shippingMethods;

        @JsonProperty("variants")
        private List<BuyMaVariantDto> variants;

        @JsonProperty("options")
        private List<BuyMaOptionDto> options;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("id")
        private  Integer id;

        @JsonProperty("duty")
        private String duty;

        @JsonProperty("colorsize_comments")
        private String colorSizeComment;


        public BuyMaProductPayload(String styleCode, long productGroupId, String name, String comments, String brandId, String brandName, String categoryId, int price, int referencePrice, List<BuyMaImageDto> images, List<BuyMaVariantDto> variants, List<BuyMaOptionDto> options, Integer id, String colorSizeComment) {
                this.referenceNumber = DEFAULT_REFERENCE_NUMBER(styleCode, productGroupId);
                this.control = "publish";
                this.name = name;
                this.comments = comments;
                this.brandId = Integer.parseInt(brandId);
                this.brandName = brandName;
                this.categoryId = Integer.parseInt(categoryId);
                this.price = price;
                this.referencePrice = referencePrice;
                this.availableUntil = LocalDateTime.now().plusDays(90).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.buyingAreaId = 2002003001;
                this.shippingAreaId = 2002003001;
                this.images = images;
                this.shippingMethods = DEFAULT_SHIPPING_METHODS;
                this.variants = variants;
                this.options = options;
                this.id = id;
                this.duty = "included";
                this.colorSizeComment = colorSizeComment;
        }

        private final static List<BuyMaShippingMethodDto> DEFAULT_SHIPPING_METHODS = List.of(
                new BuyMaShippingMethodDto(984481),
                new BuyMaShippingMethodDto(984491)
        );

        private static String DEFAULT_REFERENCE_NUMBER(String styleCode, long productGroupId) {
                StringBuilder sb = new StringBuilder();
                sb.append(productGroupId);

                if (styleCode != null && !styleCode.isEmpty()) {
                        sb.append("_");
                        sb.append(styleCode);
                }
                return sb.toString();
        }





}