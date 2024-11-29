package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductHybridRepository implements ExternalProductPersistenceRepository {

    private final ExternalProductJdbcRepository externalProductJdbcRepository;

    public ExternalProductHybridRepository(ExternalProductJdbcRepository externalProductJdbcRepository) {
        this.externalProductJdbcRepository = externalProductJdbcRepository;
    }

    @Override
    public void saveAll(List<ExternalProductEntity> externalProductEntities) {
        externalProductJdbcRepository.batchInsertExternalProducts(externalProductEntities);
    }

    @Override
    public void updateAll(List<ExternalProductEntity> externalProductEntities) {
        externalProductJdbcRepository.batchUpdateExternalProducts(externalProductEntities);
    }

    @Override
    public void updateStatusByProductGroupId(List<Long> productGroupIds, Long siteId, SyncStatus status){
        externalProductJdbcRepository.updatesSyncStatus(productGroupIds, siteId, status);
    }



}
