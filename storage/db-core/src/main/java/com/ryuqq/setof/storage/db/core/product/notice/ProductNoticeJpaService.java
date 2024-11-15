package com.ryuqq.setof.storage.db.core.product.notice;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductNoticeJpaService implements ProductNoticePersistenceService{

    private final ProductNoticeJpaRepository productNoticeJpaRepository;
    private final ProductNoticeJdbcRepository productNoticeJdbcRepository;

    public ProductNoticeJpaService(ProductNoticeJpaRepository productNoticeJpaRepository, ProductNoticeJdbcRepository productNoticeJdbcRepository) {
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
