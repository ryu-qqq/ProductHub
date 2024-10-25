package com.ryuqq.setof.storage.db.core.color;

import com.ryuqq.setof.domain.core.brand.Brand;
import com.ryuqq.setof.domain.core.brand.BrandFilter;
import com.ryuqq.setof.domain.core.color.Color;
import com.ryuqq.setof.domain.core.color.ColorFilter;
import com.ryuqq.setof.storage.db.core.BaseRepositoryTest;
import com.ryuqq.setof.storage.db.core.data.BrandModuleHelper;
import com.ryuqq.setof.storage.db.core.data.ColorModuleHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void shouldFetchColorExists() {
        boolean result = colorQueryDslRepository.fetchColorExists(color.getId());
        assertTrue(result);
    }

    @Test
    void shouldFetchBrandResponsesWithPagination() {
        ColorFilter colorFilter = ColorModuleHelper.toColorFilter(List.of());

        List<Color> colors = colorQueryDslRepository.fetchColors((colorFilter));

        assertFalse(colors.isEmpty());
        assertEquals(1, colors.size());
        assertEquals(color.getId(), colors.getFirst().id());
        assertEquals(color.getColorName(), colors.getFirst().colorName());
    }


    @Test
    void shouldReturnEmptyBrandResponsesWhenNoMatches() {
        ColorFilter colorFilter = ColorModuleHelper.toColorFilter(List.of(99L));
        List<Color> result = colorQueryDslRepository.fetchColors(colorFilter);
        assertTrue(result.isEmpty());
    }


    @Test
    void shouldReturnBrandCount() {
        ColorFilter colorFilter = ColorModuleHelper.toColorFilter(List.of());
        long count = colorQueryDslRepository.fetchColorCount(colorFilter);
        assertEquals(1, count);
    }

    @Test
    void shouldReturnZeroBrandCountWhenNoMatches() {
        ColorFilter colorFilter = ColorModuleHelper.toColorFilter(List.of(99L));
        long count = colorQueryDslRepository.fetchColorCount(colorFilter);
        assertEquals(0, count);
    }


}