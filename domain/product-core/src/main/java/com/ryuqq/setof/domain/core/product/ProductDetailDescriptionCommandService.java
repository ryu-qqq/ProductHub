package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.storage.db.core.product.description.ProductDetailDescriptionHybridRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailDescriptionCommandService {

    private final ProductDetailDescriptionHybridRepository productDetailDescriptionHybridRepository;

    public ProductDetailDescriptionCommandService(ProductDetailDescriptionHybridRepository productDetailDescriptionHybridRepository) {
        this.productDetailDescriptionHybridRepository = productDetailDescriptionHybridRepository;
    }

    public void insert(long productGroupId, ProductDetailDescriptionCommand productDetailDescriptionCommand) {
        productDetailDescriptionHybridRepository.saveAllProductDetailDescription(productDetailDescriptionCommand.toEntity(productGroupId));
    }

    public void update(long productGroupId, ProductDetailDescriptionCommand productDetailDescriptionCommand) {
        productDetailDescriptionHybridRepository.updateProductDelivery(productDetailDescriptionCommand.toEntity(productGroupId));
    }

}
