package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.dto.ProductGroupImageDto;
import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImageQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductGroupImageFinder {

    private final ProductGroupImageMapper productGroupImageMapper;
    private final ProductGroupImageQueryRepository productGroupImageQueryRepository;

    public ProductGroupImageFinder(ProductGroupImageMapper productGroupImageMapper, ProductGroupImageQueryRepository productGroupImageQueryRepository) {
        this.productGroupImageMapper = productGroupImageMapper;
        this.productGroupImageQueryRepository = productGroupImageQueryRepository;
    }


    public List<ProductGroupImage> getProductGroupImages(long productGroupId){
        List<ProductGroupImageDto> productGroupImageDtos = productGroupImageQueryRepository.fetchProductGroupImages(productGroupId);
        return productGroupImageMapper.toProductGroupImages(productGroupId, productGroupImageDtos);
    }

}
