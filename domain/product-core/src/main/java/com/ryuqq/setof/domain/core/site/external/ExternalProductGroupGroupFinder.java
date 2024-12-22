package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.db.core.site.external.ExternalProductGroupQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductGroupGroupFinder implements ExternalProductGroupQueryService {

    private final ExternalProductGroupMapper externalProductGroupMapper;
    private final ExternalProductGroupQueryRepository externalProductGroupQueryRepository;

    public ExternalProductGroupGroupFinder(ExternalProductGroupMapper externalProductGroupMapper, ExternalProductGroupQueryRepository externalProductGroupQueryRepository) {
        this.externalProductGroupMapper = externalProductGroupMapper;
        this.externalProductGroupQueryRepository = externalProductGroupQueryRepository;
    }

    @Override
    public long countByFilter(ExternalProductGroupFilter filter) {
        return externalProductGroupQueryRepository.countByFilter(filter.toStorageFilterDto());
    }

    @Override
    public List<Long> fetchUnlinkedProductGroupIds(long sellerId, List<Long> siteIds){
        return externalProductGroupQueryRepository.fetchUnlinkedProductGroupIdsBySellerIdAndSiteId(sellerId, siteIds);
    }

    @Override
    public List<ExternalProductGroup> fetchByFilter(ExternalProductGroupFilter filter){
        List<Long> externalProductGroupIds = externalProductGroupQueryRepository.fetchIdsByFilter(filter.toStorageFilterDto());
        return externalProductGroupQueryRepository.fetchByIds(externalProductGroupIds)
                .stream()
                .map(externalProductGroupMapper::toDomain)
                .toList();
    }

}
