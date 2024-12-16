package com.ryuqq.setof.domain.core.site.external;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductGroupContextFinder {

    private final ExternalProductGroupGroupFinder externalProductGroupFinder;
    private final ExternalProductGroupContextAssembler assembler;
    private final ExternalProductGroupContextMapper externalProductGroupContextMapper;

    public ExternalProductGroupContextFinder(ExternalProductGroupGroupFinder externalProductGroupFinder, ExternalProductGroupContextAssembler externalProductGroupContextAssembler, ExternalProductGroupContextMapper externalProductGroupContextMapper) {
        this.externalProductGroupFinder = externalProductGroupFinder;
        this.assembler = externalProductGroupContextAssembler;
        this.externalProductGroupContextMapper = externalProductGroupContextMapper;
    }

    public long contextByFilter(ExternalProductGroupFilter externalProductGroupFilter){
        return externalProductGroupFinder.countByFilter(externalProductGroupFilter);
    }

    public List<ExternalProductGroupContext> fetchExternalProductContextsByFilter(ExternalProductGroupFilter externalProductGroupFilter){
        List<ExternalProductGroup> externalProductGroups = externalProductGroupFinder.fetchByFilter(externalProductGroupFilter);
        if(externalProductGroups.isEmpty()) return List.of();
        ExternalProductGroupContextAggregate externalProductGroupContextAggregate = assembler.assembleContexts(externalProductGroupFilter.siteId(), externalProductGroups);

        return externalProductGroupContextMapper.toDomains(externalProductGroupContextAggregate);
    }

}
