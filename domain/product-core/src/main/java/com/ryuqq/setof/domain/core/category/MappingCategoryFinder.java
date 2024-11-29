package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.storage.db.core.category.MappingCategoryQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MappingCategoryFinder implements MappingCategoryQueryService{

    private final MappingCategoryQueryRepository mappingCategoryQueryRepository;

    public MappingCategoryFinder(MappingCategoryQueryRepository mappingCategoryQueryRepository) {
        this.mappingCategoryQueryRepository = mappingCategoryQueryRepository;
    }

    @Override
    public List<MappingCategory> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds) {
        return mappingCategoryQueryRepository.fetchByCategoryIdAndSiteId(siteId, categoryIds).stream()
                .map(c -> new MappingCategory(
                            c.getExternalCategoryId(),
                            c.getCategoryId(),
                            c.getCategoryName(),
                            c.getTargetGroup(),
                            c.getCategoryType()
                        )
                )
                .toList();

    }
}
