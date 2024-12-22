package com.ryuqq.setof.storage.db.core.site.external;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExternalSiteSellerHybridRepository implements ExternalSiteSellerPersistenceRepository {

    private final ExternalSiteSellerJpaRepository externalSiteSellerJpaRepository;

    public ExternalSiteSellerHybridRepository(ExternalSiteSellerJpaRepository externalSiteSellerJpaRepository) {
        this.externalSiteSellerJpaRepository = externalSiteSellerJpaRepository;
    }

    @Override
    public void insert(ExternalSiteSellerEntity externalSiteSellerEntity) {
        externalSiteSellerJpaRepository.save(externalSiteSellerEntity);
    }

    @Override
    public void inserts(List<ExternalSiteSellerEntity> externalSiteSellerEntities) {
        externalSiteSellerJpaRepository.saveAll(externalSiteSellerEntities);
    }


}
