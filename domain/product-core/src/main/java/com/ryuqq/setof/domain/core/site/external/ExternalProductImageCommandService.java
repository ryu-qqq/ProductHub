package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.ExternalProductImageEntity;
import com.ryuqq.setof.storage.db.core.site.external.ExternalProductImagePersistenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalProductImageCommandService {

    private final ExternalProductImagePersistenceRepository externalProductImagePersistenceRepository;

    public ExternalProductImageCommandService(ExternalProductImagePersistenceRepository externalProductImagePersistenceRepository) {
        this.externalProductImagePersistenceRepository = externalProductImagePersistenceRepository;
    }


    public void saveAllExternalProductImages(List<ExternalProductImageCommand> externalProductImageCommands){
        List<ExternalProductImageEntity> imageEntities = externalProductImageCommands.stream()
                .map(ExternalProductImageCommand::toEntity)
                .toList();
        externalProductImagePersistenceRepository.saveExternalProductImage(imageEntities);
    }


}
