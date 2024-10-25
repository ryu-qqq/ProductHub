package com.ryuqq.setof.domain.core.category;

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
        return categoryQueryRepository.fetchCategories(categoryFilter);
    }

    public List<Category> findChildCategories(long categoryId){
        return categoryQueryRepository.fetchChildCategories(categoryId);
    }

    public List<Category> findParentCategories(long categoryId){
        return categoryQueryRepository.fetchParentCategories(categoryId);
    }


    public long findCategoryCount(CategoryFilter categoryFilter){
        return categoryQueryRepository.fetchCategoryCount(categoryFilter);
    }


}
