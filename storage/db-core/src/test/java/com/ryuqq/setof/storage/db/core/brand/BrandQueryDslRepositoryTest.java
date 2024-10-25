package com.ryuqq.setof.storage.db.core.brand;

import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.brand.BrandFilter;
import com.ryuqq.setof.storage.db.core.BaseRepositoryTest;
import com.ryuqq.setof.storage.db.core.brand.dao.BrandDao;
import com.ryuqq.setof.storage.db.core.data.BrandModuleHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BrandQueryDslRepositoryTest extends BaseRepositoryTest {

    @Autowired
    BrandQueryDslRepository brandQueryDslRepository;

    protected BrandEntity brandEntity;


    @BeforeEach
    void setUp() {
        saveBrand();
    }

    protected void saveBrand(){
        BrandEntity newBrand = BrandModuleHelper.toBrandWithNoId();
        getEntityManager().persist(newBrand);
        brandEntity = newBrand;
    }

    @Test
    void shouldFetchBrandExists() {
        boolean result = brandQueryDslRepository.fetchBrandExists(brandEntity.getId());
        assertTrue(result);
    }

    @Test
    void shouldFetchBrandById() {
        BrandEntity savedBrand = this.brandEntity;

        Optional<Brand> result = brandQueryDslRepository.fetchBrand(savedBrand.getId());

        assertTrue(result.isPresent());
        assertEquals(savedBrand.getId(), result.get().id());
        assertEquals(savedBrand.getBrandName(), result.get().brandName());

    }


    @Test
    void shouldReturnEmptyWhenBrandNotFoundById() {
        Optional<Brand> result = brandQueryDslRepository.fetchBrand(999L);
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldFetchBrandResponsesWithPagination() {
        BrandFilter brandFilter = BrandModuleHelper.toBrandFilter(List.of());

        List<Brand> result = brandQueryDslRepository.fetchBrands((brandFilter));

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(brandEntity.getId(), result.getFirst().id());
        assertEquals(brandEntity.getBrandName(), result.getFirst().brandName());
    }


    @Test
    void shouldReturnEmptyBrandResponsesWhenNoMatches() {
        BrandFilter brandFilter = BrandModuleHelper.toBrandFilter(List.of(99L));

        List<Brand> result = brandQueryDslRepository.fetchBrands(brandFilter);

        assertTrue(result.isEmpty());
    }


    @Test
    void shouldReturnBrandCount() {
        BrandFilter brandFilter = BrandModuleHelper.toBrandFilter(List.of());
        long count = brandQueryDslRepository.fetchBrandCount(brandFilter);
        assertEquals(1, count);
    }

    @Test
    void shouldReturnZeroBrandCountWhenNoMatches() {
        BrandFilter brandFilter = BrandModuleHelper.toBrandFilter(List.of(99L));
        long count = brandQueryDslRepository.fetchBrandCount(brandFilter);
        assertEquals(0, count);
    }

}