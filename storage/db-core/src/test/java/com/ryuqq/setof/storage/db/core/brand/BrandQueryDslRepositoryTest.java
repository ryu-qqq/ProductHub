package com.ryuqq.setof.storage.db.core.brand;

import com.ryuqq.setof.storage.db.core.BaseRepositoryTest;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandDto;
import com.ryuqq.setof.storage.db.core.brand.dto.BrandStorageFilterDto;
import com.ryuqq.setof.storage.db.core.data.BrandModuleHelper;
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
    void shouldExistById() {
        boolean result = brandQueryDslRepository.existById(brandEntity.getId());
        assertTrue(result);
    }

    @Test
    void shouldFetchByIdById() {
        BrandEntity savedBrand = this.brandEntity;

        Optional<BrandDto> result = brandQueryDslRepository.fetchById(savedBrand.getId());

        assertTrue(result.isPresent());
        assertEquals(savedBrand.getId(), result.get().getId());
        assertEquals(savedBrand.getBrandName(), result.get().getBrandName());

    }


    @Test
    void shouldReturnEmptyWhenBrandNotFoundById() {
        Optional<BrandDto> result = brandQueryDslRepository.fetchById(999L);
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldFetchByIdResponsesWithPagination() {
        BrandStorageFilterDto brandFilter = BrandModuleHelper.toBrandFilter(List.of());

        List<BrandDto> result = brandQueryDslRepository.fetchByFilter((brandFilter));

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(brandEntity.getId(), result.getFirst().getId());
        assertEquals(brandEntity.getBrandName(), result.getFirst().getBrandName());
    }


    @Test
    void shouldReturnEmptyBrandResponsesWhenNoMatches() {
        BrandStorageFilterDto brandFilter = BrandModuleHelper.toBrandFilter(List.of(99L));

        List<BrandDto> result = brandQueryDslRepository.fetchByFilter(brandFilter);

        assertTrue(result.isEmpty());
    }


    @Test
    void shouldReturnBrandCount() {
        BrandStorageFilterDto brandFilter = BrandModuleHelper.toBrandFilter(List.of());
        long count = brandQueryDslRepository.countByFilter(brandFilter);
        assertEquals(1, count);
    }

    @Test
    void shouldReturnZeroBrandCountWhenNoMatches() {
        BrandStorageFilterDto brandFilter = BrandModuleHelper.toBrandFilter(List.of(99L));
        long count = brandQueryDslRepository.countByFilter(brandFilter);
        assertEquals(0, count);
    }

}