package com.ryuqq.setof.storage.db.core.product.dto;


import com.querydsl.core.annotations.QueryProjection;

import java.util.List;

public class ProductGroupContextDto {
    private ProductGroupDto productGroupDto;
    private ProductNoticeDto productNoticeDto;
    private ProductDeliveryDto productDeliveryDto;
    private List<ProductGroupImageDto> productGroupImageDtos;
    private ProductDetailDescriptionDto productDetailDescriptionDto;


    protected ProductGroupContextDto() {}

    @QueryProjection
    public ProductGroupContextDto(ProductGroupDto productGroupDto, ProductNoticeDto productNoticeDto, ProductDeliveryDto productDeliveryDto, List<ProductGroupImageDto> productGroupImageDtos, ProductDetailDescriptionDto productDetailDescriptionDto) {
        this.productGroupDto = productGroupDto;
        this.productNoticeDto = productNoticeDto;
        this.productDeliveryDto = productDeliveryDto;
        this.productGroupImageDtos = productGroupImageDtos;
        this.productDetailDescriptionDto = productDetailDescriptionDto;
    }

    public ProductGroupDto getProductGroupDto() {
        return productGroupDto;
    }

    public ProductNoticeDto getProductNoticeDto() {
        return productNoticeDto;
    }

    public ProductDeliveryDto getProductDeliveryDto() {
        return productDeliveryDto;
    }

    public List<ProductGroupImageDto> getProductGroupImageDtos() {
        return productGroupImageDtos;
    }

    public ProductDetailDescriptionDto getProductDetailDescriptionDto() {
        return productDetailDescriptionDto;
    }
}
