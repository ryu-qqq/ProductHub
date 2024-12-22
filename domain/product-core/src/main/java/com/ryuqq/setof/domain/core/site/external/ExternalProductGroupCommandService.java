package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;
import com.ryuqq.setof.db.core.site.external.ExternalProductGroupEntity;
import com.ryuqq.setof.db.core.site.external.ExternalProductGroupPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductGroupCommandService {

    private final ExternalProductGroupPersistenceRepository externalProductGroupPersistenceRepository;

    public ExternalProductGroupCommandService(ExternalProductGroupPersistenceRepository externalProductGroupPersistenceRepository) {
        this.externalProductGroupPersistenceRepository = externalProductGroupPersistenceRepository;
    }

    public void saveAllExternalProductGroup(List<ExternalProductGroupEntity> externalProductEntities){
        externalProductGroupPersistenceRepository.saveAll(externalProductEntities);
    }

    public void updateExternalProductGroup(List<ExternalProductGroupUpdateCommand> externalProductGroupUpdateCommands){
        List<ExternalProductGroupEntity> entities = externalProductGroupUpdateCommands.stream().map(ExternalProductGroupUpdateCommand::toEntity).toList();
        externalProductGroupPersistenceRepository.updateAll(entities);
    }

    public void updateSyncStatus(List<Long> productGroupIds, Long siteId,SyncStatus status){
        externalProductGroupPersistenceRepository.updateStatusByProductGroupId(productGroupIds, siteId, status);
    }



}
