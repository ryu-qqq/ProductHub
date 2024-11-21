package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionCommandFacade;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailDescriptionCommandService {

    private final ProductDetailDescriptionCommandFacade productDetailDescriptionCommandFacade;

    public ProductDetailDescriptionCommandService(ProductDetailDescriptionCommandFacade productDetailDescriptionCommandFacade) {
        this.productDetailDescriptionCommandFacade = productDetailDescriptionCommandFacade;
    }

    public void insert(long productGroupId, ProductDetailDescriptionCommand productDetailDescriptionCommand) {
        productDetailDescriptionCommandFacade.insert(productDetailDescriptionCommand.toEntity(productGroupId));
    }

    public void update(long productGroupId, ProductDetailDescriptionCommand productDetailDescriptionCommand) {
        productDetailDescriptionCommandFacade.update(productDetailDescriptionCommand.toEntity(productGroupId));
    }

}
