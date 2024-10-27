package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.storage.db.core.product.image.ProductGroupImagePersistenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductGroupImageCommandService {

    private final ProductGroupImagePersistenceService productGroupImagePersistenceService;

    public ProductGroupImageCommandService(ProductGroupImagePersistenceService productGroupImagePersistenceService) {
        this.productGroupImagePersistenceService = productGroupImagePersistenceService;
    }

    public void inserts(long productGroupId, List<ProductGroupImageCommand> productGroupImageCommands) {
        productGroupImageCommands.forEach(productGroupImageCommand -> {
            productGroupImagePersistenceService.insert(productGroupImageCommand.toEntity(productGroupId));
        });
    }

}
