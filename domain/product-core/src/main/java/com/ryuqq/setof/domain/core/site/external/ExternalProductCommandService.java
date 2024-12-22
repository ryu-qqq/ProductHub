package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.db.core.site.external.ExternalProductEntity;
import com.ryuqq.setof.db.core.site.external.ExternalProductPersistenceRepository;
import com.ryuqq.setof.db.core.site.external.ExternalProductUpdateCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductCommandService {

    private final ExternalProductPersistenceRepository externalProductPersistenceRepository;

    public ExternalProductCommandService(ExternalProductPersistenceRepository externalProductPersistenceRepository) {
        this.externalProductPersistenceRepository = externalProductPersistenceRepository;
    }

    public void saveAllExternalProduct(List<ExternalProductUpdateCommand> externalProductUpdateCommands){
        List<ExternalProductEntity> externalProductEntities = externalProductUpdateCommands.stream().map(ExternalProductUpdateCommand::toEntity).toList();
        externalProductPersistenceRepository.saveAll(externalProductEntities);
    }

    public void updateExternalProducts(List<ExternalProductUpdateCommand> externalProductUpdateCommands){
        List<ExternalProductEntity> externalProductEntities = externalProductUpdateCommands.stream().map(ExternalProductUpdateCommand::toEntity).toList();
        externalProductPersistenceRepository.updateAll(externalProductEntities);
    }

}
