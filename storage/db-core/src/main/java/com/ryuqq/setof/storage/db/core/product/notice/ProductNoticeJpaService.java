package com.ryuqq.setof.storage.db.core.product.notice;

import org.springframework.stereotype.Service;

@Service
public class ProductNoticeJpaService implements ProductNoticePersistenceService{

    private final ProductNoticeJpaRepository productNoticeJpaRepository;

    public ProductNoticeJpaService(ProductNoticeJpaRepository productNoticeJpaRepository) {
        this.productNoticeJpaRepository = productNoticeJpaRepository;
    }

    @Override
    public void insert(ProductNoticeEntity productNoticeEntity) {
        productNoticeJpaRepository.save(productNoticeEntity);
    }
}
