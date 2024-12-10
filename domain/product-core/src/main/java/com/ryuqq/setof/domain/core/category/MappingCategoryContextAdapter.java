package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.support.external.core.shein.SheInCategoryQueryService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MappingCategoryContextAdapter {

    private final SheInCategoryQueryService sheInCategoryQueryService;

    public MappingCategoryContextAdapter(SheInCategoryQueryService sheInCategoryQueryService) {
        this.sheInCategoryQueryService = sheInCategoryQueryService;
    }

    public List<MappingCategoryCommand> toDomains(long siteId){
        return sheInCategoryQueryService.fetchSheInCategory().stream()
                .map(e -> new MappingCategoryCommand(
                        e.externalCategoryId(),
                        e.externalExtraCategoryId(),
                        0L,
                        siteId,
                        e.description()
                ))
                .toList();
    }


}
