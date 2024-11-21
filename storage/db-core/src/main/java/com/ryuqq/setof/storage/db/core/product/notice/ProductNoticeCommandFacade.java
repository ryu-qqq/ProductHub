package com.ryuqq.setof.storage.db.core.product.notice;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductNoticeCommandFacade implements ProductNoticePersistenceService{

    private final ProductNoticeJpaRepository productNoticeJpaRepository;
    private final ProductNoticeJdbcRepository productNoticeJdbcRepository;

    public ProductNoticeCommandFacade(ProductNoticeJpaRepository productNoticeJpaRepository, ProductNoticeJdbcRepository productNoticeJdbcRepository) {
        this.productNoticeJpaRepository = productNoticeJpaRepository;
        this.productNoticeJdbcRepository = productNoticeJdbcRepository;
    }

    @Override
    public void insert(ProductNoticeEntity productNoticeEntity) {
        productNoticeJpaRepository.save(productNoticeEntity);
    }

    @Override
    public void insertAll(List<ProductNoticeEntity> productNoticeEntities) {
        productNoticeJdbcRepository.batchInsertProductNotices(productNoticeEntities);
    }

    @Override
    public void update(ProductNoticeEntity productNoticeEntity) {
        updateAll(List.of(productNoticeEntity));
    }

    @Override
    public void updateAll(List<ProductNoticeEntity> productNoticeEntities) {
        int[] rowsAffected = productNoticeJdbcRepository.batchUpdateProductNotices(productNoticeEntities);
        if(rowsAffected.length != productNoticeEntities.size()) {

        }
    }

}
