package com.ryuqq.setof.storage.db.core.site;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExternalSiteSellerJpaService implements ExternalSiteSellerPersistenceService{

    private final ExternalSiteSellerJpaRepository externalSiteSellerJpaRepository;

    public ExternalSiteSellerJpaService(ExternalSiteSellerJpaRepository externalSiteSellerJpaRepository) {
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
