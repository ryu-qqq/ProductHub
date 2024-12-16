package com.ryuqq.setof.storage.db.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductGroupGroupHybridRepository implements ExternalProductGroupPersistenceRepository {

    private final ExternalProductGroupJdbcRepository externalProductGroupJdbcRepository;

    public ExternalProductGroupGroupHybridRepository(ExternalProductGroupJdbcRepository externalProductGroupJdbcRepository) {
        this.externalProductGroupJdbcRepository = externalProductGroupJdbcRepository;
    }

    @Override
    public void saveAll(List<ExternalProductGroupEntity> externalProductEntities) {
        externalProductGroupJdbcRepository.batchInsertExternalProductGroups(externalProductEntities);
    }

    @Override
    public void updateAll(List<ExternalProductGroupEntity> externalProductEntities) {
        externalProductGroupJdbcRepository.batchUpdateExternalProductGroups(externalProductEntities);
    }

    @Override
    public void updateStatusByProductGroupId(List<Long> productGroupIds, Long siteId, SyncStatus status){
        externalProductGroupJdbcRepository.updatesSyncStatus(productGroupIds, siteId, status);
    }



}
