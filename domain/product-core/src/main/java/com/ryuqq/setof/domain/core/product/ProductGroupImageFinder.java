package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupImageDto;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupImageFinder {

    private final ProductGroupImageQueryRepository productGroupImageQueryRepository;

    public ProductGroupImageFinder(ProductGroupImageQueryRepository productGroupImageQueryRepository) {
        this.productGroupImageQueryRepository = productGroupImageQueryRepository;
    }


    public List<ProductGroupImage> getProductGroupImages(long productGroupId){
        List<ProductGroupImageDto> images = productGroupImageQueryRepository.fetchProductGroupImages(productGroupId);
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
