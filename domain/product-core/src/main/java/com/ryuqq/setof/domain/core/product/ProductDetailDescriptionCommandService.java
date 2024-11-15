package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionJpaService;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailDescriptionCommandService {

    private final ProductDetailDescriptionJpaService productDetailDescriptionJpaService;

    public ProductDetailDescriptionCommandService(ProductDetailDescriptionJpaService productDetailDescriptionJpaService) {
        this.productDetailDescriptionJpaService = productDetailDescriptionJpaService;
    }

    public void insert(long productGroupId, ProductDetailDescriptionCommand productDetailDescriptionCommand) {
        productDetailDescriptionJpaService.insert(productDetailDescriptionCommand.toEntity(productGroupId));
    }

    public void update(long productGroupId, ProductDetailDescriptionCommand productDetailDescriptionCommand) {
        productDetailDescriptionJpaService.update(productDetailDescriptionCommand.toEntity(productGroupId));
    }

}