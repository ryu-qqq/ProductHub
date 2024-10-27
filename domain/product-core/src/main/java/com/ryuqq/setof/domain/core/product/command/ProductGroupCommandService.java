package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.storage.db.core.product.group.ProductGroupPersistenceService;
import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticePersistenceService;
import org.springframework.stereotype.Service;

@Service
public class ProductGroupCommandService {

    private final ProductGroupPersistenceService productGroupPersistenceService;

    public ProductGroupCommandService(ProductGroupPersistenceService productGroupPersistenceService, ProductNoticePersistenceService productNoticePersistenceService) {
        this.productGroupPersistenceService = productGroupPersistenceService;
    }

    public long insert(ProductGroupCommand productGroupCommand) {
        return productGroupPersistenceService.insert(productGroupCommand.toEntity());
    }
}
