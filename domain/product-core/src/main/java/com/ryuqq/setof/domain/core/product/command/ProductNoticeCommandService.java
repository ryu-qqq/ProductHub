package com.ryuqq.setof.domain.core.product.command;

import com.ryuqq.setof.storage.db.core.product.notice.ProductNoticePersistenceService;
import org.springframework.stereotype.Service;

@Service
public class ProductNoticeCommandService {

    private final ProductNoticePersistenceService productNoticePersistenceService;

    public ProductNoticeCommandService(ProductNoticePersistenceService productNoticePersistenceService) {
        this.productNoticePersistenceService = productNoticePersistenceService;
    }

    public void insert(long productGroupId, ProductNoticeCommand productNoticeCommand) {
        productNoticePersistenceService.insert(productNoticeCommand.toEntity(productGroupId));
    }

}
