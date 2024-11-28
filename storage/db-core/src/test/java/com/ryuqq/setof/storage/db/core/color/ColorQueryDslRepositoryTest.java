package com.ryuqq.setof.storage.db.core.color;

import com.ryuqq.setof.storage.db.core.BaseRepositoryTest;
import com.ryuqq.setof.storage.db.core.color.dto.ColorDto;
import com.ryuqq.setof.storage.db.core.color.dto.ColorStorageFilterDto;
import com.ryuqq.setof.storage.db.core.data.ColorModuleHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ColorQueryDslRepositoryTest extends BaseRepositoryTest {

    @Autowired
    ColorQueryDslRepository colorQueryDslRepository;


    protected ColorEntity color;

    @BeforeEach
    void setUp() {
        saveColor();
    }


    protected void saveColor(){
        ColorEntity colorEntity = ColorModuleHelper.toColorWithNoId();
        getEntityManager().persist(colorEntity);
        color = colorEntity;
    }


    @Test
    void shouldExistById() {
        boolean result = colorQueryDslRepository.existById(color.getId());
        assertTrue(result);
    }

    @Test
    void shouldFetchBrandResponsesWithPagination() {
        ColorStorageFilterDto colorFilter = ColorModuleHelper.toColorFilter(List.of());

        List<ColorDto> colors = colorQueryDslRepository.fetchColorByFilter((colorFilter));

        assertFalse(colors.isEmpty());
        assertEquals(1, colors.size());
        assertEquals(color.getId(), colors.getFirst().getId());
        assertEquals(color.getColorName(), colors.getFirst().getColorName());
    }


    @Test
    void shouldReturnEmptyBrandResponsesWhenNoMatches() {
        ColorStorageFilterDto colorFilter = ColorModuleHelper.toColorFilter(List.of(99L));
        List<ColorDto> result = colorQueryDslRepository.fetchColorByFilter(colorFilter);
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldReturnBrandCount() {
        ColorStorageFilterDto colorFilter = ColorModuleHelper.toColorFilter(List.of());
        long count = colorQueryDslRepository.countByFilter(colorFilter);
        assertEquals(1, count);
    }

    @Test
    void shouldReturnZeroBrandCountWhenNoMatches() {
        ColorStorageFilterDto colorFilter = ColorModuleHelper.toColorFilter(List.of(99L));
        long count = colorQueryDslRepository.countByFilter(colorFilter);
        assertEquals(0, count);
    }


}