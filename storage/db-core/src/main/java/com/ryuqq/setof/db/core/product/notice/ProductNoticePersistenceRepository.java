package com.ryuqq.setof.storage.db.core.product.notice;

import java.util.List;

public interface ProductNoticePersistenceRepository {

    void insertProductNotice(ProductNoticeEntity productNoticeEntity);
    void insertAllProductNotice(List<ProductNoticeEntity> productNoticeEntities);
    void updateProductNotice(ProductNoticeEntity productNoticeEntity);
    void updateAllProductNotice(List<ProductNoticeEntity> productNoticeEntities);
}
