package com.ryuqq.setof.storage.db.core.product.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGroupJpaRepository extends JpaRepository<ProductGroupEntity, Long> {

}
