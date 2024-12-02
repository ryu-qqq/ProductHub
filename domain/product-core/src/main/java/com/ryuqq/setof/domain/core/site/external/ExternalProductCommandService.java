package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.enums.core.SyncStatus;

import com.ryuqq.setof.storage.db.core.site.external.ExternalProductEntity;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductCommandService {

    private final ExternalProductPersistenceRepository externalProductPersistenceRepository;

    public ExternalProductCommandService(ExternalProductPersistenceRepository externalProductPersistenceRepository) {
        this.externalProductPersistenceRepository = externalProductPersistenceRepository;
    }

    public void saveAllExternalProduct(List<ExternalProductEntity> externalProductEntities){
        externalProductPersistenceRepository.saveAll(externalProductEntities);
    }

    public void updateExternalProduct(List<ExternalProductUpdateCommand> externalProductUpdateCommands){
        List<ExternalProductEntity> entities = externalProductUpdateCommands.stream().map(ExternalProductUpdateCommand::toEntity).toList();
        externalProductPersistenceRepository.updateAll(entities);
    }



}
