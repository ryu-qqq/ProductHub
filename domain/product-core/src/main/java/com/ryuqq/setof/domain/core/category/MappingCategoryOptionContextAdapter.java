package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.support.external.core.shein.SheInAttributeQueryService;
import com.ryuqq.setof.support.external.core.shein.SheInCategoryQueryService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MappingCategoryOptionContextAdapter {

    private final SheInAttributeQueryService sheInAttributeQueryService;

    public MappingCategoryOptionContextAdapter(SheInAttributeQueryService sheInAttributeQueryService) {
        this.sheInAttributeQueryService = sheInAttributeQueryService;
    }

    public List<MappingCategoryOptionCommand> toDomains(long siteId, List<Long> extraCategoryIds){

        return extraCategoryIds.stream()
                .flatMap(aLong -> sheInAttributeQueryService.fetchSheInAttributes(aLong).stream())
                .map(e -> new MappingCategoryOptionCommand(
                        siteId,
                        e.externalCategoryId(),
                        e.optionGroupId(),
                        e.optionId(),
                        e.optionValue()
                )).distinct()
                .toList();
    }


}
