package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.option.ProductQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFinder {

    private final ProductMapper productMapper;
    private final ProductQueryRepository productQueryRepository;

    public ProductFinder(ProductMapper productMapper, ProductQueryRepository productQueryRepository) {
        this.productMapper = productMapper;
        this.productQueryRepository = productQueryRepository;
    }

    public List<Product> fetchByProductGroupIds(List<Long> productGroupIds){
        return productMapper.toDomains(
                        productQueryRepository.fetchByProductGroupIds(productGroupIds)
        );
    }


}
