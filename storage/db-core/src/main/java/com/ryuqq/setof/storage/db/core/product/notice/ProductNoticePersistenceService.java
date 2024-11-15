package com.ryuqq.setof.storage.db.core.product.notice;

import java.util.List;

public interface ProductNoticePersistenceService {

    void insert(ProductNoticeEntity productNoticeEntity);
    void insertAll(List<ProductNoticeEntity> productNoticeEntities);
    void update(ProductNoticeEntity productNoticeEntity);
    void updateAll(List<ProductNoticeEntity> productNoticeEntities);
}
