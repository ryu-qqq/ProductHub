package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.ExternalRequestEntity;
import com.ryuqq.setof.storage.db.core.site.external.ExternalRequestPersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalRequestCommandService {

    private final ExternalRequestPersistenceRepository externalRequestPersistenceRepository;

    public ExternalRequestCommandService(ExternalRequestPersistenceRepository externalRequestPersistenceRepository) {
        this.externalRequestPersistenceRepository = externalRequestPersistenceRepository;
    }

    public void saveAllExternalRequest(List<ExternalRequestCommand> externalRequestCommands){
        List<ExternalRequestEntity> entities = externalRequestCommands.stream().map(ExternalRequestCommand::toEntity).toList();
        externalRequestPersistenceRepository.saveAllExternalRequest(entities);
    }



}
