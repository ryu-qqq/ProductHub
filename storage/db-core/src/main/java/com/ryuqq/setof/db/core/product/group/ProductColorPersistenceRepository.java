package com.ryuqq.setof.storage.db.core.product.group;

import java.util.List;

public interface ProductColorPersistenceRepository {

    void saveAllProductColorEntities(List<ProductColorEntity> productColorEntities);

}
