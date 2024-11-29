package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.ExternalCategoryOptionQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalCategoryOptionFinder implements ExternalCategoryOptionQueryService {

    private final ExternalCategoryOptionQueryRepository externalCategoryOptionQueryRepository;

    public ExternalCategoryOptionFinder(ExternalCategoryOptionQueryRepository externalCategoryOptionQueryRepository) {
        this.externalCategoryOptionQueryRepository = externalCategoryOptionQueryRepository;
    }

    @Override
    public List<ExternalCategoryOption> fetchBySiteIdAndCategoryIds(long siteId, List<Long> categoryIds) {
        return externalCategoryOptionQueryRepository.fetchBySiteIdAndCategoryIds(siteId, categoryIds).stream()
                .map(e -> new ExternalCategoryOption(
                        e.getSiteId(),
                        e.getExternalCategoryId(),
                        e.getOptionId(),
                        e.getOptionValue()
                )).toList();
    }


}
