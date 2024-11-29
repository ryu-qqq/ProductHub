package com.ryuqq.setof.domain.core.site.external;

import com.ryuqq.setof.storage.db.core.site.external.ExternalProductQueryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalProductFinder implements ExternalProductQueryService {

    private final ExternalProductMapper externalProductMapper;
    private final ExternalProductQueryRepository externalProductQueryRepository;

    public ExternalProductFinder(ExternalProductMapper externalProductMapper, ExternalProductQueryRepository externalProductQueryRepository) {
        this.externalProductMapper = externalProductMapper;
        this.externalProductQueryRepository = externalProductQueryRepository;
    }


    @Override
    public List<Long> findUnlinkedProductGroupIds(long sellerId, List<Long> siteIds){
        return externalProductQueryRepository.fetchUnlinkedProductGroupIdsBySellerIdAndSiteId(sellerId, siteIds);
    }


    @Override
    public List<ExternalProduct> fetchByFilter(ExternalProductFilter filter){
        return externalProductQueryRepository.fetchByFilter(filter.toStorageFilterDto())
                .stream()
                .map(externalProductMapper::toDomain)
                .toList();
    }

}
