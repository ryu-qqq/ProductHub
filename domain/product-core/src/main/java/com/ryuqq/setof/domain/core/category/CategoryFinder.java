package com.ryuqq.setof.domain.core.category;

import com.ryuqq.setof.storage.db.core.category.CategoryQueryRepository;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryFinder {

    private final CategoryQueryRepository categoryQueryRepository;


    public CategoryFinder(CategoryQueryRepository categoryQueryRepository) {
        this.categoryQueryRepository = categoryQueryRepository;
    }

    public boolean categoryExist(long categoryId){
        return categoryQueryRepository.fetchCategoryExists(categoryId);
    }


    public List<Category> findCategories(CategoryFilter categoryFilter){
        List<CategoryDto> categoryDtos = categoryQueryRepository.fetchCategories(categoryFilter.toStorageFilter());
        return toCategories(categoryDtos);
    }

    public List<Category> findChildCategories(long categoryId){
        List<CategoryDto> categoryDtos = categoryQueryRepository.fetchChildCategories(categoryId);
        return toCategories(categoryDtos);
    }

    public List<Category> findParentCategories(long categoryId){
        List<CategoryDto> categoryDtos = categoryQueryRepository.fetchParentCategories(categoryId);
        return toCategories(categoryDtos);
    }


    public long findCategoryCount(CategoryFilter categoryFilter){
        return categoryQueryRepository.fetchCategoryCount(categoryFilter.toStorageFilter());
    }


    private List<Category> toCategories(List<CategoryDto> categoryDtos){
        return categoryDtos.stream()
                .map(c ->
                    new Category(c.getId(), c.getCategoryName(), c.getDepth(), c.getParentCategoryId(),
                            c.isDisplayYn(), c.getTargetGroup(), c.getCategoryType(), c.getPath())
            ).toList();
    }


}
