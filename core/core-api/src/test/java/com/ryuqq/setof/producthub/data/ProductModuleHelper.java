package com.ryuqq.setof.producthub.data;

import com.ryuqq.setof.core.*;
import com.ryuqq.setof.domain.core.product.query.ProductGroup;
import com.ryuqq.setof.producthub.core.api.controller.v1.product.request.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductModuleHelper {

    public static ProductGroupInsertRequestDto toProductGroupInsertRequestDto(String productGroupName, BigDecimal regularPrice, BigDecimal currentPrice, ProductCondition productCondition) {
        return new ProductGroupInsertRequestDto(1L, 2L, 3L, productGroupName, "Style123", productCondition, ManagementType.MENUAL, OptionType.OPTION_ONE, regularPrice, currentPrice, false, true);
    }


    public static ProductGroupCommandContextRequestDto toProductGroupCommandContextRequestDto() {
        ProductGroupInsertRequestDto productGroupInsertRequestDto = new ProductGroupInsertRequestDto(1L, 2L, 3L, "Test Product Group", "Style123", ProductCondition.NEW, ManagementType.MENUAL, OptionType.OPTION_ONE, BigDecimal.valueOf(1000), BigDecimal.valueOf(800), false, true);
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




//    public static ProductGroup toProductGroup(){
//        return new ProductGroup(1L, 1L, );
//    }



}
