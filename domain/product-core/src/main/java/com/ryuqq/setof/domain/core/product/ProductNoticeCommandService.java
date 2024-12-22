package com.ryuqq.setof.domain.core.product;

import com.ryuqq.setof.db.core.product.notice.ProductNoticePersistenceRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductNoticeCommandService {

    private final ProductNoticePersistenceRepository productNoticePersistenceRepository;

    public ProductNoticeCommandService(ProductNoticePersistenceRepository productNoticePersistenceRepository) {
        this.productNoticePersistenceRepository = productNoticePersistenceRepository;
    }

    public void insert(long productGroupId, ProductNoticeCommand productNoticeCommand) {
        productNoticePersistenceRepository.insertProductNotice(productNoticeCommand.toEntity(productGroupId));
    }

    public void update(long productGroupId, ProductNoticeCommand productNoticeCommand) {
        productNoticePersistenceRepository.updateProductNotice(productNoticeCommand.toEntity(productGroupId));
    }

}
