package com.ryuqq.setof.storage.db.core.data;

import com.ryuqq.setof.enums.core.CategoryType;
import com.ryuqq.setof.enums.core.TargetGroup;
import com.ryuqq.setof.storage.db.core.category.CategoryEntity;
import com.ryuqq.setof.storage.db.core.category.dto.CategoryStorageFilterDto;

import java.util.ArrayList;
import java.util.List;

public class CategoryModuleHelper {

    public static List<CategoryEntity> generateCategories() {
        List<CategoryEntity> categories = new ArrayList<>();

        categories.add(new CategoryEntity( "women",1, 0L, true,  TargetGroup.FEMALE, CategoryType.NONE,"1"));
        categories.add(new CategoryEntity( "의류", 2,1, true,  TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2"));
        categories.add(new CategoryEntity( "탑", 3,2, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3"));
        categories.add(new CategoryEntity( "스웨트셔츠", 4,3, true,  TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2,3,4"));
        categories.add(new CategoryEntity( "후드", 4,3, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3,5"));
        categories.add(new CategoryEntity( "티셔츠", 4,3,  true, TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2,3,6"));
        categories.add(new CategoryEntity( "폴로", 4,3, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3,7"));
        categories.add(new CategoryEntity( "뷔스티에", 4,3, true,  TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3,8"));
        categories.add(new CategoryEntity( "스웨터", 3,2, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3,9"));
        categories.add(new CategoryEntity( "가디건", 4,9, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,9,10"));
        categories.add(new CategoryEntity( "라운드넥", 4,9, true, TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2,9,11"));
        categories.add(new CategoryEntity( "브이넥",4, 9, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,9,12"));
        categories.add(new CategoryEntity( "집업", 4,9, true,  TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,9,13"));
        categories.add(new CategoryEntity( "터틀넥", 4,9, true,  TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,9,14"));
        categories.add(new CategoryEntity( "베스트", 4,9, true,  TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,9,15"));
        return categories;
    }


    public static List<CategoryEntity> generateChildCategories() {
        List<CategoryEntity> categories = new ArrayList<>();

        categories.add(new CategoryEntity( "women",1, 0L, true,  TargetGroup.FEMALE, CategoryType.NONE,"1"));
        categories.add(new CategoryEntity( "의류", 2,1, true,  TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2"));
        categories.add(new CategoryEntity( "탑", 3,2, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3"));
        categories.add(new CategoryEntity( "스웨트셔츠", 4,3, true,  TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2,3,4"));
        categories.add(new CategoryEntity( "후드", 4,3, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3,5"));
        categories.add(new CategoryEntity( "티셔츠", 4,3,  true, TargetGroup.FEMALE,CategoryType.CLOTHING,"1,2,3,6"));
        categories.add(new CategoryEntity( "폴로", 4,3, true,   TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3,7"));
        categories.add(new CategoryEntity( "뷔스티에", 4,3, true,  TargetGroup.FEMALE, CategoryType.CLOTHING,"1,2,3,8"));
        return categories;
    }

    public static CategoryStorageFilterDto toCategoryFilter(List<CategoryEntity> categories){
        List<Long> categoryIds =
                categories.isEmpty() ?
                        List.of(999L) : categories.stream().map(CategoryEntity::getId).toList();

        return new CategoryStorageFilterDto(categoryIds, null, null, null, 20);
    }
}
