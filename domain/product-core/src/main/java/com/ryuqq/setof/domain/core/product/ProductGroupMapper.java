package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupMapper {

    public ProductGroup toDomain(ProductGroupDto dto){
            return new ProductGroup(
                    dto.getProductGroupId(),
                    dto.getSetOfProductGroupId(),
                    dto.getSellerId(),
                    dto.getConfigId(),
                    dto.getColorIds(),
                    dto.getCategoryId(),
                    dto.getBrandId(),
                    dto.getProductGroupName(),
                    dto.getStyleCode(),
                    dto.getProductCondition(),
                    dto.getManagementType(),
                    dto.getOptionType(),
                    new Price(dto.getRegularPrice(), dto.getCurrentPrice()),
                    dto.isSoldOutYn(),
                    dto.isDisplayYn(),
                    dto.getProductStatus(),
                    dto.getKeywords(),
                    toProductDelivery(dto.getProductGroupId(), dto.getProductDeliveryDto()),
                    toProductNotice(dto.getProductGroupId(), dto.getProductNoticeDto()),
                    toProductGroupImages(dto.getProductGroupId(), dto.getProductGroupImageDtos()),
                    toProductDetailDescription(dto.getProductGroupId(), dto.getProductDetailDescriptionDto())
            );

    }


    private ProductNotice toProductNotice(long productGroupId, ProductNoticeDto dto) {
        return new ProductNotice(
                productGroupId,
                dto.getMaterial(),
                dto.getColor(),
                dto.getSize(),
                dto.getMaker(),
                dto.getOrigin(),
                dto.getWashingMethod(),
                dto.getYearMonth(),
                dto.getAssuranceStandard(),
                dto.getAsPhone()
        );
    }

    private ProductDelivery toProductDelivery(long productGroupId, ProductDeliveryDto dto) {
        return new ProductDelivery(
                productGroupId,
                dto.getDeliveryArea(),
                dto.getDeliveryFee(),
                dto.getDeliveryPeriodAverage(),
                dto.getReturnMethodDomestic(),
                dto.getReturnCourierDomestic(),
                dto.getReturnChargeDomestic(),
                dto.getReturnExchangeAreaDomestic()
        );
    }

    private List<ProductGroupImage> toProductGroupImages(long productGroupId, List<ProductGroupImageDto> images) {
        return images.stream()
                .map(dto ->
                        new ProductGroupImage(
                                dto.getProductGroupImageId(),
                                productGroupId,
                                dto.getProductImageType(),
                                dto.getImageUrl(),
                                dto.getOriginUrl())
                )
                .toList();
    }


    private ProductDetailDescription toProductDetailDescription(long productGroupId, ProductDetailDescriptionDto dto) {
        return new ProductDetailDescription(productGroupId, dto.getDetailDescription());
    }




}
