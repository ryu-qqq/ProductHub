package com.ryuqq.setof.domain.core.site;

import com.ryuqq.setof.storage.db.core.site.StandardSizeQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StandardSizeFinder implements StandardSizeQueryService {

    private final StandardSizeQueryRepository standardSizeQueryRepository;

    public StandardSizeFinder(StandardSizeQueryRepository standardSizeQueryRepository) {
        this.standardSizeQueryRepository = standardSizeQueryRepository;
    }

    @Override
    public List<StandardSize> fetchByCategoryIds(List<Long> categoryIds) {
        return standardSizeQueryRepository.fetchByCategoryIds(categoryIds)
                .stream()
                .map(s -> new StandardSize(
                        s.getCategoryId(),
                        s.getRegionId(),
                        s.getName(),
                        s.getSizeValue()
                ))
                .toList();
    }

}
