package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.dto.ProductContextDto;
import com.ryuqq.setof.storage.db.core.product.option.ProductQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ProductFinder {

    private final ProductMapper productMapper;
    private final ProductQueryRepository productQueryRepository;

    public ProductFinder(ProductMapper productMapper, ProductQueryRepository productQueryRepository) {
        this.productMapper = productMapper;
        this.productQueryRepository = productQueryRepository;
    }

    public Set<Product> findProducts(List<Long> productGroupIds){
        List<ProductContextDto> productContextDtos = productQueryRepository.fetchProductContexts(productGroupIds);
        return productMapper.toProductContexts(productContextDtos);
    }


}
