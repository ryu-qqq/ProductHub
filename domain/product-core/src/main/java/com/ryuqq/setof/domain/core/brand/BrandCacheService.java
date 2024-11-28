package com.ryuqq.setof.domain.core.brand;

import com.ryuqq.setof.storage.db.cache.brand.BrandCacheRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class BrandCacheService {

    private final BrandCacheRepository brandCacheRepository;
    private final BrandMapper brandMapper;

    public BrandCacheService(BrandCacheRepository brandCacheRepository, BrandMapper brandMapper) {
        this.brandCacheRepository = brandCacheRepository;
        this.brandMapper = brandMapper;
    }

    public Optional<Brand> findBrandFromCache(long brandId) {
        return brandCacheRepository.fetchById(brandId)
                .map(brandMapper::toDomain);
    }

    public void saveBrandToCache(Brand brand, Duration ttl) {
        brandCacheRepository.saveBrand(brand.id(), brandMapper.toCacheDto(brand), ttl);
    }

    public void saveBrandsToCache(List<Brand> brands, Duration ttl) {
        brands.forEach(brand -> saveBrandToCache(brand, ttl));
    }
}
