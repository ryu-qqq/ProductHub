package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.storage.db.core.brand.MappingBrandQueryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MappingBrandFinder implements MappingBrandQueryService{

    private final MappingBrandQueryRepository mappingBrandQueryRepository;

    public MappingBrandFinder(MappingBrandQueryRepository mappingBrandQueryRepository) {
        this.mappingBrandQueryRepository = mappingBrandQueryRepository;
    }

    @Override
    public List<MappingBrand> getMappingBrands(long siteId, List<Long> brandIds) {
        return mappingBrandQueryRepository.fetchByBrandIdAndSiteId(siteId, brandIds)
                .stream()
                .map(b -> new MappingBrand(
                        b.getExternalBrandId(),
                        b.getBrandId(),
                        b.getBrandName()
                )).toList();
    }

}
