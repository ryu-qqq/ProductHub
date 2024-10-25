package com.ryuqq.setof.storage.db.core.category;

import com.ryuqq.setof.domain.core.category.Category;
import com.ryuqq.setof.domain.core.category.CategoryFilter;
import com.ryuqq.setof.storage.db.core.BaseRepositoryTest;
import com.ryuqq.setof.storage.db.core.category.dao.CategoryDao;
import com.ryuqq.setof.storage.db.core.data.CategoryModuleHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CategoryQueryDslRepositoryTest extends BaseRepositoryTest {


    @Autowired
    CategoryQueryDslRepository categoryQueryDslRepository;

    private CategoryEntity category;
    private List<CategoryEntity> categories;

    @BeforeEach
    void setUp() {
        saveCategories();
        Random random = new Random();
        category = categories.get(random.nextInt(categories.size()));
    }

    protected void saveCategories(){
        List<CategoryEntity> mockCategories = CategoryModuleHelper.generateCategories();
        mockCategories.forEach(category -> {
            getEntityManager().persist(category);
        });
        categories = mockCategories;
    }


    @Test
    void shouldFetchCategoryExists() {
        boolean result = categoryQueryDslRepository.fetchCategoryExists(category.getId());
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenCategoryNotExists() {
        boolean result = categoryQueryDslRepository.fetchCategoryExists(999L);
        assertFalse(result);
    }

    @Test
    void shouldFetchChildCategories() {
        List<Category> result = categoryQueryDslRepository.fetchChildCategories(category.getId());
        assertNotNull(result);
        assertFalse(result.isEmpty());
        result.forEach(child -> {
            assertTrue(child.depth() >= category.getDepth());
        });
    }


    @Test
    void shouldFetchParentCategories() {
        List<Category> result = categoryQueryDslRepository.fetchParentCategories(category.getId());
        assertNotNull(result);
        assertFalse(result.isEmpty());
        result.forEach(parent -> {
            assertTrue(parent.depth() <= category.getDepth());
        });
    }


    @Test
    void shouldFetchCategoryResponsesWithPagination() {
        CategoryFilter filter = CategoryModuleHelper.toCategoryFilter(categories);

        List<Category> result = categoryQueryDslRepository.fetchCategories(filter);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        result.forEach(response -> {
            assertTrue(categories.stream().anyMatch(c -> c.getId() == (response.id())));
        });
    }

    @Test
    void shouldReturnEmptyCategoryResponsesWhenNoMatches() {
        CategoryFilter filter = CategoryModuleHelper.toCategoryFilter(List.of());

        List<Category> result = categoryQueryDslRepository.fetchCategories(filter);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnCategoryCount() {
        CategoryFilter filter = CategoryModuleHelper.toCategoryFilter(categories);
        long count = categoryQueryDslRepository.fetchCategoryCount(filter);

        assertEquals(categories.size(), count);
    }

    @Test
    void shouldReturnZeroCategoryCountWhenNoMatches() {
        CategoryFilter filter = CategoryModuleHelper.toCategoryFilter(List.of());
        long count = categoryQueryDslRepository.fetchCategoryCount(filter);

        assertEquals(0, count);
    }
}