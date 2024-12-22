package com.ryuqq.setof.storage.db.core.product.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductGroupInsertRequestJpaRepository extends JpaRepository<ProductGroupInsertRequestEntity, Long> {

    List<ProductGroupInsertRequestEntity> findProductGroupInsertRequestEntitiesByProductGroupIdIn(List<Long> productGroupIds);

}
