package com.ryuqq.setof.domain.core.product.query;

import com.ryuqq.setof.storage.db.core.product.group.ProductGroupEntity;
import com.ryuqq.setof.storage.db.core.product.group.ProductGroupQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupFinder {

    private final ProductGroupQueryService productGroupQueryService;

    public ProductGroupFinder(ProductGroupQueryService productGroupQueryService) {
        this.productGroupQueryService = productGroupQueryService;
    }

    public List<ProductGroupEntity> getProductGroupEntities(){
        return productGroupQueryService.findAll();

    }
}
