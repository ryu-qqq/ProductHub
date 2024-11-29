package com.ryuqq.setof.api.core.data;

import com.ryuqq.setof.api.core.controller.v1.product.request.*;
import com.ryuqq.setof.api.core.controller.v1.product.response.*;
import com.ryuqq.setof.api.core.controller.v1.site.response.ExternalMallProductPendingDataResponse;
import com.ryuqq.setof.enums.core.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ProductModuleHelper {

    public static ProductGroupInsertRequestDto toProductGroupInsertRequestDto(String productGroupName, BigDecimal regularPrice, BigDecimal currentPrice, ProductCondition productCondition) {
        return new ProductGroupInsertRequestDto(1L,1L, 2L, 3L, productGroupName, "Style123", productCondition, ManagementType.MENUAL, OptionType.OPTION_ONE, regularPrice, currentPrice, false, false,  "");
    }


    public static ProductGroupCommandContextRequestDto toProductGroupCommandContextRequestDto() {
        ProductGroupInsertRequestDto productGroupInsertRequestDto = new ProductGroupInsertRequestDto(1L, 1L, 2L, 3L, "Test Product Group", "Style123", ProductCondition.NEW, ManagementType.MENUAL, OptionType.OPTION_ONE, BigDecimal.valueOf(1000), BigDecimal.valueOf(800), false, true, "");
        ProductNoticeInsertRequestDto productNoticeInsertRequestDto = new ProductNoticeInsertRequestDto("Cotton", "Blue", "M", "BrandX", Origin.KR, "Dry Clean", "2022-09", "Standard", "010-1234-5678");
        ProductDeliveryRequestDto productDeliveryRequestDto = new ProductDeliveryRequestDto("Seoul", BigDecimal.valueOf(10), 2, ReturnMethod.RETURN_CONSUMER, ShipmentCompanyCode.SHIP01, BigDecimal.valueOf(5), "Seoul");
        ProductGroupImageRequestDto productGroupImageRequestDto = new ProductGroupImageRequestDto(ProductImageType.MAIN, "http://image.url");
        ProductInsertRequestDto productInsertRequestDto = new ProductInsertRequestDto(false, true, 10, BigDecimal.valueOf(100), List.of(new ProductOptionInsertRequestDto(OptionName.SIZE, "M")));

        return new ProductGroupCommandContextRequestDto(
                productGroupInsertRequestDto,
                productNoticeInsertRequestDto,
                productDeliveryRequestDto,
                List.of(productGroupImageRequestDto),
                new ProductGroupDetailDescriptionRequestDto("Detailed Description"),
                List.of(productInsertRequestDto)
        );
    }


    public static ProductGroupResponse toProductGroupResponse(long productGroupId, OptionType optionType){
        return new ProductGroupResponse(
                productGroupId,
                101L,
                "Sample Product Group",
                "SGX001",
                ProductCondition.NEW,
                ManagementType.MENUAL,
                optionType,
                toPrice(100000, 50000, 50),
                false,
                true,
                ProductStatus.WAITING,
                "",
                toProductDeliveryResponse(),
                toProductNoticeResponse(),
                toProductDetailDescriptionResponse(),
                toProductGroupImageResponse()
        );
    }

    public static ProductGroupConfigContextResponse toProductGroupConfigContextResponse(){
        return new ProductGroupConfigContextResponse(
                toProductGroupConfigResponse(),
                List.of(toProductGroupNameConfigResponse()),
                List.of()
        );
    }

    public static ProductGroupConfigResponse toProductGroupConfigResponse(){
        return new ProductGroupConfigResponse(1L, 1L, true);
    }

    public static ProductGroupNameConfigResponse toProductGroupNameConfigResponse(){
        return new ProductGroupNameConfigResponse(Origin.US, "");
    }


    public static PriceResponse toPrice(long regularPrice, long currentPrice, int discountRate){
        return new PriceResponse(new BigDecimal(regularPrice), new BigDecimal(currentPrice), new BigDecimal(currentPrice), new BigDecimal(regularPrice- currentPrice), discountRate, discountRate);
    }


    public static ProductDeliveryResponse toProductDeliveryResponse(){
        return new ProductDeliveryResponse(
                "Domestic",
                new BigDecimal("5.00"),
                3,
                ReturnMethod.RETURN_CONSUMER,
                ShipmentCompanyCode.SHIP01,
                new BigDecimal("3.00"),
                "123 Warehouse St."
        );
    }

    public static ProductNoticeResponse toProductNoticeResponse(){
        return new ProductNoticeResponse(
                "Plastic",
                "Red",
                "Medium",
                "BrandX",
                Origin.KR,
                "Hand wash only",
                "2023-08",
                "ISO Certified",
                "123-456-7890"
        );
    }

    public static List<ProductGroupImageResponse> toProductGroupImageResponse(){
        return List.of(
                new ProductGroupImageResponse(ProductImageType.MAIN, "https://images.com/product-main.jpg"),
                new ProductGroupImageResponse(ProductImageType.DETAIL, "https://images.com/product-thumb.jpg")
        );
    }

    public static ProductDetailDescriptionResponse toProductDetailDescriptionResponse(){
        return new ProductDetailDescriptionResponse(
                "This is a sample product description with detailed information."
        );
    }

    public static List<ProductResponse> toProducts(long productGroupId, OptionType optionType){
        switch (optionType) {
            case SINGLE:
                return List.of(new ProductResponse(productGroupId, 4L, 10, false, true, "", Collections.emptySet(), BigDecimal.ZERO));
            case OPTION_ONE:
                return List.of(
                        new ProductResponse(productGroupId, 3L, 10,false, true, "", Set.of(new OptionResponse(3L, 3L, 4L, OptionName.SIZE, "M")), BigDecimal.ZERO),
                        new ProductResponse(productGroupId, 3L,5,false, true, "", Set.of(new OptionResponse(3L, 3L, 5L, OptionName.SIZE, "L")), BigDecimal.ZERO)
                );
            case OPTION_TWO:
                return List.of(
                        new ProductResponse(productGroupId, 1L, 10,false, true, "",
                                Set.of(
                                        new OptionResponse(1L, 1L, 1L, OptionName.COLOR, "Black"),
                                        new OptionResponse(1L, 2L, 2L, OptionName.SIZE, "M")

                                ), BigDecimal.ZERO),

                        new ProductResponse(productGroupId, 2L,5,false, true, "",
                                Set.of(
                                        new OptionResponse(2L, 1L, 1L, OptionName.COLOR, "Black"),
                                        new OptionResponse(2L, 2L, 3L, OptionName.SIZE, "L")

                                ), BigDecimal.ZERO)
                );

            default:
                return Collections.emptyList();
        }

    }

    public static ProductGroupContextResponse toProductGroupContextResponse(long productGroupId, OptionType optionType) {
        return new ProductGroupContextResponse(
                toProductGroupResponse(productGroupId, optionType),
                toProducts(productGroupId, optionType),
                CategoryModuleHelper.toCategoryResponses(),
                BrandModuleHelper.toBrandResponse(),
                toProductGroupConfigContextResponse()
        );
    }

    public static ExternalMallProductPendingDataResponse toExternalMallProductPendingDataResponse(){
        return new ExternalMallProductPendingDataResponse(
                1L,
                1L,
                1L,
                Origin.US,
                true
        );
    }


}
