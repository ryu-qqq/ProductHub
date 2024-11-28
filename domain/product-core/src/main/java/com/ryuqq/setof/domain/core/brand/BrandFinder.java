package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.domain.core.exception.NotFoundException;
import com.ryuqq.setof.storage.db.core.brand.BrandQueryRepository;
import com.ryuqq.setof.storage.db.index.brand.BrandDocumentQueryRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class BrandFinder implements BrandQueryService {

    private final BrandQueryRepository brandQueryRepository;
    private final BrandDocumentQueryRepository brandDocumentQueryRepository;
    private final BrandCacheService brandCacheService;
    private final BrandMapper brandMapper;

    public BrandFinder(BrandQueryRepository brandQueryRepository, BrandDocumentQueryRepository brandDocumentQueryRepository, BrandCacheService brandCacheService, BrandMapper brandMapper) {
        this.brandQueryRepository = brandQueryRepository;
        this.brandDocumentQueryRepository = brandDocumentQueryRepository;
        this.brandCacheService = brandCacheService;
        this.brandMapper = brandMapper;
    }
    @Override
    public boolean existById(long brandId){
        return brandQueryRepository.existById(brandId);
    }

    @Override
    public long countByFilter(BrandFilter brandFilter){
        return brandQueryRepository.countByFilter(brandFilter.toStorageFilter());
    }

    @Override
    public Brand fetchBrand(long brandId) {
        return brandCacheService.findBrandFromCache(brandId)
                .orElseGet(() -> {
                    Brand brand = brandQueryRepository.fetchById(brandId)
                            .map(brandMapper::toDomain)
                            .orElseThrow(() -> new NotFoundException(String.format(BrandErrorConstants.BRAND_NOT_FOUND_ERROR_MSG, brandId)));
                    brandCacheService.saveBrandToCache(brand, Duration.ofHours(1));
                    return brand;
                });
    }

    @Override
    public List<Brand> fetchBrandsByFilter(BrandFilter brandFilter) {
        if (brandFilter.hasBrandName()) {
            List<Long> brandIdsFromEs = brandDocumentQueryRepository.fetchByBrandName(brandFilter.brandName());
            brandFilter = brandFilter.withBrandIds(brandIdsFromEs);
        }

        return brandQueryRepository.fetchByFilter(brandFilter.toStorageFilter()).stream()
                .map(brandMapper::toDomain)
                .toList();
    }

    @Override
    public List<Brand> fetchBrandsByIds(List<Long> brandIds) {
        Map<Long, Brand> cachedBrandMap = brandIds.stream()
                .map(id -> Map.entry(id, brandCacheService.findBrandFromCache(id)))
                .filter(entry -> entry.getValue().isPresent())
                .collect(
                        Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().get(), (v1, v2) -> v2)
                );

        List<Long> missingIds = brandIds.stream()
                .filter(id -> !cachedBrandMap.containsKey(id))
                .toList();

        List<Brand> result = new ArrayList<>(cachedBrandMap.values());

        if (!missingIds.isEmpty()) {
            BrandFilter brandFilter = BrandFilter.of(missingIds, null, null, missingIds.size());
            List<Brand> brandsFromRds = brandQueryRepository.fetchByFilter(brandFilter.toStorageFilter())
                    .stream()
                    .map(brandMapper::toDomain)
                    .toList();

            brandCacheService.saveBrandsToCache(brandsFromRds, Duration.ofHours(1));
            result.addAll(brandsFromRds);
        }

        return result;
    }


}
