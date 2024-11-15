package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.domain.core.brand.BrandRecord;
import com.ryuqq.setof.domain.core.category.CategoryRecord;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryDto;
import com.ryuqq.setof.storage.db.core.product.dto.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProductGroupContextMapper {

    private final ProductGroupImageMapper productGroupImageMapper;

    public ProductGroupContextMapper(ProductGroupImageMapper productGroupImageMapper) {
        this.productGroupImageMapper = productGroupImageMapper;
    }

    public ProductGroupContext toProductGroupContext(ProductGroupContextDto dto) {
        long productGroupId = dto.getProductGroupDto().getProductGroupId();
        return new ProductGroupContext(
                toProductGroup(dto.getProductGroupDto()),
                toProductDelivery(productGroupId, dto.getProductDeliveryDto()),
                toProductNotice(productGroupId, dto.getProductNoticeDto()),
                toProductGroupImages(productGroupId, dto.getProductGroupImageDtos()),
                toProductDetailDescription(productGroupId, dto.getProductDetailDescriptionDto()),
                Collections.emptySet()
        );
    }

    private ProductGroup toProductGroup(ProductGroupDto dto) {
        return new ProductGroup(
                dto.getProductGroupId(),
                dto.getSellerId(),
                null,
                List.of(toCategory(dto.getCategoryDto())),
                toBrand(dto.getBrandDto()),
                dto.getProductGroupName(),
                dto.getStyleCode(),
                dto.getProductCondition(),
                dto.getManagementType(),
                dto.getOptionType(),
                new Price(dto.getRegularPrice(), dto.getCurrentPrice(), dto.getDiscountRate()),
                dto.isSoldOutYn(),
                dto.isDisplayYn(),
                dto.getProductStatus(),
                dto.getKeywords()
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

    private ProductDetailDescription toProductDetailDescription(long productGroupId, ProductDetailDescriptionDto dto) {
        return new ProductDetailDescription(productGroupId, dto.getDetailDescription());
    }

    private List<ProductGroupImage> toProductGroupImages(long productGroupId, List<ProductGroupImageDto> images) {
        return productGroupImageMapper.toProductGroupImages(productGroupId, images);
    }

    private CategoryRecord toCategory(CategoryDto dto) {
        return CategoryRecord.toCategoryRecord(dto);
    }

    private BrandRecord toBrand(BrandDto dto) {
        return BrandRecord.toBrandRecord(dto);
    }
}
