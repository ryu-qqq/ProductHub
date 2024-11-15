package com.ryuqq.setof.storage.db.core.product.notice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductNoticeJpaRepository extends JpaRepository<ProductNoticeEntity, Long> {

    Optional<ProductNoticeEntity> findByProductGroupId(long productGroupId);
}
