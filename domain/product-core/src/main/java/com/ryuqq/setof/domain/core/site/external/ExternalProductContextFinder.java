package com.ryuqq.setof.domain.core.site.external;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductContextFinder {

    private final ExternalProductFinder externalProductFinder;
    private final ExternalProductContextAssembler assembler;
    private final ExternalProductContextMapper externalProductContextMapper;

    public ExternalProductContextFinder(ExternalProductFinder externalProductFinder, ExternalProductContextAssembler externalProductContextAssembler, ExternalProductContextMapper externalProductContextMapper) {
        this.externalProductFinder = externalProductFinder;
        this.assembler = externalProductContextAssembler;
        this.externalProductContextMapper = externalProductContextMapper;
    }


    public long contextByFilter(ExternalProductFilter externalProductFilter){
        return externalProductFinder.countByFilter(externalProductFilter);
    }


    public List<ExternalProductContext> fetchExternalProductContextsByFilter(ExternalProductFilter externalProductFilter){
        List<ExternalProduct> externalProducts = externalProductFinder.fetchByFilter(externalProductFilter);
        if(externalProducts.isEmpty()) return List.of();
        ExternalProductContextAggregate externalProductContextAggregate = assembler.assembleContexts(externalProductFilter.siteId(), externalProducts);

        return externalProductContextMapper.toDomains(externalProductContextAggregate);
    }

}
