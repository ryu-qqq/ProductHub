package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryDto;
import com.ryuqq.setof.storage.db.core.product.dto.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductGroupContextMapper {

    public ProductGroupContext toProductGroupContext(ProductGroupContextDto dto) {
        return new ProductGroupContext(
                toProductGroup(dto.getProductGroupDto()),
                toProductDelivery(dto.getProductDeliveryDto()),
                toProductNotice(dto.getProductNoticeDto()),
                toProductGroupImages(dto.getProductGroupImageDtos()),
                toProductDetailDescription(dto.getProductDetailDescriptionDto()),
                Collections.emptySet()
        );
    }

    private ProductGroup toProductGroup(ProductGroupDto dto) {
        return new ProductGroup(
                dto.getProductGroupId(),
                dto.getSellerId(),
                toCategory(dto.getCategoryDto()),
                toBrand(dto.getBrandDto()),
                dto.getProductGroupName(),
                dto.getStyleCode(),
                dto.getProductCondition(),
                dto.getManagementType(),
                dto.getOptionType(),
                new Price(dto.getRegularPrice(), dto.getCurrentPrice(), dto.getDiscountRate()),
                dto.isSoldOutYn(),
                dto.isDisplayYn(),
                dto.getProductStatus()
        );
    }

    private ProductNotice toProductNotice(ProductNoticeDto dto) {
        return new ProductNotice(
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

    private ProductDelivery toProductDelivery(ProductDeliveryDto dto) {
        return new ProductDelivery(
                dto.getDeliveryArea(),
                dto.getDeliveryFee(),
                dto.getDeliveryPeriodAverage(),
                dto.getReturnMethodDomestic(),
                dto.getReturnCourierDomestic(),
                dto.getReturnChargeDomestic(),
                dto.getReturnExchangeAreaDomestic()
        );
    }

    private ProductDetailDescription toProductDetailDescription(ProductDetailDescriptionDto dto) {
        return new ProductDetailDescription(dto.getDetailDescription());
    }

    private List<ProductGroupImage> toProductGroupImages(List<ProductGroupImageDto> imageDtos) {
        return imageDtos.stream()
                .map(dto -> new ProductGroupImage(dto.getProductImageType(), dto.getImageUrl()))
                .toList();
    }

    private Category toCategory(CategoryDto dto) {
        return new Category(
                dto.getId(),
                dto.getCategoryName(),
                dto.getDepth(),
                dto.getParentCategoryId(),
                dto.isDisplayYn(),
                dto.getTargetGroup(),
                dto.getCategoryType(),
                dto.getPath()
        );
    }

    private Brand toBrand(BrandDto dto) {
        return new Brand(dto.getId(), dto.getBrandName(), dto.getBrandIconImageUrl(), dto.isDisplayYn());
    }
}
