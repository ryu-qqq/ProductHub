package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.db.core.category.MappingCategoryEntity;
import com.ryuqq.setof.db.core.category.MappingCategoryPersistenceRepository;
import com.ryuqq.setof.db.core.site.external.ExternalCategoryOptionEntity;
import com.ryuqq.setof.db.core.site.external.ExternalCategoryOptionPersistenceRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingCategoryCommandService {

    private final MappingCategoryQueryService mappingCategoryQueryService;
    private final MappingCategoryContextAdapter mappingCategoryContextAdapter;
    private final MappingCategoryOptionContextAdapter mappingCategoryOptionContextAdapter;
    private final MappingCategoryPersistenceRepository mappingCategoryPersistenceRepository;
    private final ExternalCategoryOptionPersistenceRepository externalCategoryOptionPersistenceRepository;

    public MappingCategoryCommandService(MappingCategoryQueryService mappingCategoryQueryService, MappingCategoryContextAdapter mappingCategoryContextAdapter, MappingCategoryOptionContextAdapter mappingCategoryOptionContextAdapter, MappingCategoryPersistenceRepository mappingCategoryPersistenceRepository, ExternalCategoryOptionPersistenceRepository externalCategoryOptionPersistenceRepository) {
        this.mappingCategoryQueryService = mappingCategoryQueryService;
        this.mappingCategoryContextAdapter = mappingCategoryContextAdapter;
        this.mappingCategoryOptionContextAdapter = mappingCategoryOptionContextAdapter;
        this.mappingCategoryPersistenceRepository = mappingCategoryPersistenceRepository;
        this.externalCategoryOptionPersistenceRepository = externalCategoryOptionPersistenceRepository;
    }

    @Transactional
    public int saveAll(long siteId){
        List<MappingCategoryEntity> mappingCategoryEntities = mappingCategoryContextAdapter.toDomains(siteId).stream()
                .map(c -> new MappingCategoryEntity(
                        c.siteId(),
                        c.externalCategoryId(),
                        c.externalExtraCategoryId(),
                        c.description(),
                        c.categoryId()
                ))
                .toList();
        mappingCategoryPersistenceRepository.saveAll(mappingCategoryEntities);

        return mappingCategoryEntities.size();
    }

    @Transactional
    public int saveAllMappingCategoryOptions(long siteId){
        List<MappingCategory> mappingCategories = mappingCategoryQueryService.fetchBySiteIdAndCategoryIds(siteId, List.of(1447L));

        List<Long> extraCategoryIds = mappingCategories.stream()
                .map(m -> Long.parseLong(m.externalExtraCategoryId()))
                .distinct()
                .toList();


        List<ExternalCategoryOptionEntity> externalCategoryOptionEntities = mappingCategoryOptionContextAdapter.toDomains(siteId, extraCategoryIds).stream()
                .map(c -> new ExternalCategoryOptionEntity(
                        c.siteId(),
                        String.valueOf(c.externalCategoryId()),
                        c.optionGroupId(),
                        c.optionId(),
                        c.optionValue()
                ))
                .toList();

        externalCategoryOptionPersistenceRepository.saveAll(externalCategoryOptionEntities);

        return externalCategoryOptionEntities.size();
    }








}
