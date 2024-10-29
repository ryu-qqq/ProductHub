package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.storage.db.core.product.dto.ProductContextDto;
import com.ryuqq.setof.storage.db.core.product.option.ProductQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ProductFinder {

    private final ProductContextMapper productContextMapper;
    private final ProductQueryRepository productQueryRepository;

    public ProductFinder(ProductContextMapper productContextMapper, ProductQueryRepository productQueryRepository) {
        this.productContextMapper = productContextMapper;
        this.productQueryRepository = productQueryRepository;
    }

    public Set<ProductContext> getProductGroupContexts(List<Long> productGroupIds){
        List<ProductContextDto> productContextDtos = productQueryRepository.fetchProductContexts(productGroupIds);
        return productContextMapper.toProductContexts(productContextDtos);
    }

}
