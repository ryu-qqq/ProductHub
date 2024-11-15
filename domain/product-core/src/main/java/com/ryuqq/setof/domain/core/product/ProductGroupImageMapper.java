package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupImageDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupImageMapper {

    public List<ProductGroupImage> toProductGroupImages(long productGroupId, List<ProductGroupImageDto> images) {
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
}
