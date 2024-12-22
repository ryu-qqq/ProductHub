package com.ryuqq.setof.storage.db.core.product.notice;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductNoticeHybridRepository implements ProductNoticePersistenceRepository {

    private final ProductNoticeJpaRepository productNoticeJpaRepository;
    private final ProductNoticeJdbcRepository productNoticeJdbcRepository;

    public ProductNoticeHybridRepository(ProductNoticeJpaRepository productNoticeJpaRepository, ProductNoticeJdbcRepository productNoticeJdbcRepository) {
        this.productNoticeJpaRepository = productNoticeJpaRepository;
        this.productNoticeJdbcRepository = productNoticeJdbcRepository;
    }

    @Override
    public void insertProductNotice(ProductNoticeEntity productNoticeEntity) {
        productNoticeJpaRepository.save(productNoticeEntity);
    }

    @Override
    public void insertAllProductNotice(List<ProductNoticeEntity> productNoticeEntities) {
        productNoticeJdbcRepository.batchInsertProductNotices(productNoticeEntities);
    }

    @Override
    public void updateProductNotice(ProductNoticeEntity productNoticeEntity) {
        updateAllProductNotice(List.of(productNoticeEntity));
    }

    @Override
    public void updateAllProductNotice(List<ProductNoticeEntity> productNoticeEntities) {
        int[] rowsAffected = productNoticeJdbcRepository.batchUpdateProductNotices(productNoticeEntities);
        if(rowsAffected.length != productNoticeEntities.size()) {

        }
    }

}
